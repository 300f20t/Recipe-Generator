package net.mcreator.recipe_generator.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.Minecraft;

import net.mcreator.recipe_generator.RecipeGeneratorModPlayerAnimationAPI;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;

@Mixin(PlayerModel.class)
public abstract class PlayerAnimationMixin<T extends LivingEntity> {
	private String master = null;
	private Minecraft mc = Minecraft.getInstance();

	@Inject(method = "setupAnim", at = @At(value = "HEAD"))
	public void setupPivot(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
		if (master == null)
			master = "recipe_generator";
		if (!master.equals("recipe_generator"))
			return;
		PlayerModel<T> model = (PlayerModel<T>) (Object) this;
		resetModelPose(model);
		Player player = null;
		if (entityIn instanceof Player player_)
			player = player_;
		else
			return;
		RecipeGeneratorModPlayerAnimationAPI.PlayerAnimation animation = RecipeGeneratorModPlayerAnimationAPI.active_animations.get(player);
		if (animation == null)
			return;
		if (animation.bones.get("left_arm") != null || animation.bones.get("torso") != null || animation.bones.get("right_arm") != null)
			model.attackTime = 0;
		model.crouching = false;
	}

	@Inject(method = "setupAnim", at = @At(value = "TAIL"))
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
		if (ageInTicks <= 0)
			return;
		if (!master.equals("recipe_generator")) {
			if (!RecipeGeneratorModPlayerAnimationAPI.animations.isEmpty())
				RecipeGeneratorModPlayerAnimationAPI.animations.clear();
			return;
		}
		PlayerModel<T> model = (PlayerModel<T>) (Object) this;
		Player player = null;
		if (entityIn instanceof Player player_)
			player = player_;
		else
			return;
		CompoundTag data = player.getPersistentData();
		String playingAnimation = data.getString("PlayerCurrentAnimation");
		boolean overrideAnimation = data.getBoolean("OverrideCurrentAnimation");
		boolean firstPerson = data.getBoolean("FirstPersonAnimation") && mc.options.getCameraType().isFirstPerson() && player == mc.player && mc.screen == null;
		if (data.getBoolean("ResetPlayerAnimation")) {
			data.remove("ResetPlayerAnimation");
			data.remove("LastAnimationProgress");
			data.remove("PlayedSoundTimes");
			RecipeGeneratorModPlayerAnimationAPI.active_animations.put(player, null);
		}
		if (playingAnimation.isEmpty()) {
			return;
		}
		if (firstPerson) {
			player.yBodyRotO = player.yHeadRotO;
			player.yBodyRot = player.yHeadRot;
		}
		if (overrideAnimation) {
			data.putBoolean("OverrideCurrentAnimation", false);
			data.remove("PlayerAnimationProgress");
			data.remove("LastAnimationProgress");
			data.remove("PlayedSoundTimes");
			firstPerson = data.getBoolean("FirstPersonAnimation") && mc.options.getCameraType().isFirstPerson() && player == mc.player && mc.screen == null;
			RecipeGeneratorModPlayerAnimationAPI.active_animations.put(player, null);
		}
		RecipeGeneratorModPlayerAnimationAPI.PlayerAnimation animation = RecipeGeneratorModPlayerAnimationAPI.active_animations.get(player);
		if (animation == null) {
			animation = RecipeGeneratorModPlayerAnimationAPI.animations.get(playingAnimation);
			if (animation == null) {
				RecipeGeneratorMod.LOGGER.info("Attepted to play null animation " + playingAnimation + ", did animations fail to load?");
				return;
			}
			RecipeGeneratorModPlayerAnimationAPI.active_animations.put(player, animation);
		}
		float animationProgress;
		float lastAnimationProgress = data.getFloat("LastAnimationProgress");
		ListTag playedSoundsTag = data.getList("PlayedSoundTimes", Tag.TAG_FLOAT);
		if (!data.contains("PlayerAnimationProgress")) {
			animationProgress = 0f;
			data.putFloat("PlayerAnimationProgress", animationProgress);
			data.putFloat("LastTickTime", ageInTicks);
			data.putFloat("LastAnimationProgress", 0f);
		} else {
			animationProgress = data.getFloat("PlayerAnimationProgress");
			float lastTickTime = data.getFloat("LastTickTime");
			float deltaTime = (ageInTicks - lastTickTime) / 20f; // Convert ticks to seconds
			animationProgress += deltaTime;
			data.putFloat("PlayerAnimationProgress", animationProgress);
			data.putFloat("LastTickTime", ageInTicks);
			if (animationProgress >= animation.length) {
				if (!animation.hold_on_last_frame && !animation.loop) {
					data.remove("PlayerCurrentAnimation");
					data.remove("PlayerAnimationProgress");
					data.remove("LastAnimationProgress");
					data.remove("PlayedSoundTimes");
					data.putBoolean("ResetPlayerAnimation", true);
					data.putBoolean("FirstPersonAnimation", false);
					RecipeGeneratorModPlayerAnimationAPI.active_animations.put(player, null);
					animationProgress = animation.length;
				} else if (animation.hold_on_last_frame) {
					data.putFloat("PlayerAnimationProgress", animation.length);
				} else if (animation.loop) {
					data.remove("PlayerAnimationProgress");
					data.remove("LastAnimationProgress");
					data.remove("PlayedSoundTimes");
				}
			}
		}
		if (!animation.soundEffects.isEmpty()) {
			Set<Float> playedSoundTimes = new HashSet<>();
			for (int i = 0; i < playedSoundsTag.size(); i++) {
				playedSoundTimes.add(playedSoundsTag.getFloat(i));
			}
			// Play any sound keyframes
			for (Map.Entry<Float, String> soundEntry : animation.soundEffects.entrySet()) {
				float soundTime = soundEntry.getKey();
				String soundId = soundEntry.getValue();
				if (playedSoundTimes.contains(soundTime)) {
					continue;
				}
				boolean shouldPlay = false;
				if (lastAnimationProgress <= animationProgress) {
					shouldPlay = lastAnimationProgress <= soundTime && animationProgress >= soundTime;
				} else {
					shouldPlay = lastAnimationProgress <= soundTime || animationProgress >= soundTime;
				}
				if (shouldPlay && player.level() instanceof ClientLevel clientLevel) {
					clientLevel.playLocalSound(player.getX(), player.getY(), player.getZ(), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse(soundId)), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
					playedSoundsTag.add(FloatTag.valueOf(soundTime));
				}
			}
			data.put("PlayedSoundTimes", playedSoundsTag);
			data.putFloat("LastAnimationProgress", animationProgress);
		}
		if (!data.getBoolean("FirstPersonAnimation") && mc.options.getCameraType().isFirstPerson() && player == mc.player && mc.screen == null)
			return;
		// Apply each bone's transformations
		for (Map.Entry<String, RecipeGeneratorModPlayerAnimationAPI.PlayerBone> entry : animation.bones.entrySet()) {
			String boneName = entry.getKey();
			RecipeGeneratorModPlayerAnimationAPI.PlayerBone bone = entry.getValue();
			ModelPart modelPart = getModelPart(model, boneName);
			if (modelPart == null)
				continue;
			// Apply rotation
			Vec3 rotation = RecipeGeneratorModPlayerAnimationAPI.PlayerBone.interpolate(bone.rotations, animationProgress);
			if (rotation != null) {
				modelPart.xRot = (float) Math.toRadians(rotation.x);
				modelPart.yRot = (float) Math.toRadians(rotation.y);
				modelPart.zRot = (float) Math.toRadians(rotation.z);
			}
			// Apply position (don't apply if null - keep default position)
			Vec3 position = RecipeGeneratorModPlayerAnimationAPI.PlayerBone.interpolate(bone.positions, animationProgress);
			if (position != null) {
				// Position offsets are relative, not absolute
				modelPart.x += (float) position.x;
				modelPart.y -= (float) position.y;
				modelPart.z += (float) position.z;
			}
			// Apply scale
			Vec3 scale = RecipeGeneratorModPlayerAnimationAPI.PlayerBone.interpolate(bone.scales, animationProgress);
			if (scale != null) {
				modelPart.xScale = (float) scale.x;
				modelPart.yScale = (float) scale.y;
				modelPart.zScale = (float) scale.z;
			}
			boolean firstPersonArms = firstPerson && (boneName.equals("right_arm") || boneName.equals("left_arm"));
			if (firstPersonArms) {
				float frameBuffer = 0.09f;
				float timeLeft = animation.length - animationProgress;
				float fpWeight = 1.0f;
				boolean rightArm = boneName.equals("right_arm");
				if (!animation.loop && timeLeft < frameBuffer) {
					fpWeight = Math.max(0f, timeLeft / frameBuffer);
				}
				if (fpWeight > 0) {
					float pitchRadians = (float) Math.toRadians(player.getXRot());
					modelPart.xRot += pitchRadians * fpWeight;
					float yRotCorrection = pitchRadians * (rightArm ? -0.42f : 0.42f);
					modelPart.yRot += yRotCorrection * fpWeight;
					float zRotCorrection = pitchRadians * (rightArm ? -0.34f : 0.34f);
					modelPart.zRot += zRotCorrection * fpWeight;
					float originalY = modelPart.y;
					float originalZ = modelPart.z;
					float cosP = (float) Math.cos(pitchRadians);
					float sinP = (float) Math.sin(pitchRadians);
					float targetY = originalY * cosP - originalZ * sinP;
					float targetZ = originalY * sinP + originalZ * cosP;
					modelPart.y = originalY + (targetY - originalY) * fpWeight;
					modelPart.z = originalZ + (targetZ - originalZ) * fpWeight;
				}
			}
		}
		model.leftPants.copyFrom(model.leftLeg);
		model.rightPants.copyFrom(model.rightLeg);
		model.leftSleeve.copyFrom(model.leftArm);
		model.rightSleeve.copyFrom(model.rightArm);
		model.jacket.copyFrom(model.body);
		model.hat.copyFrom(model.head);
	}

	private void resetModelPose(PlayerModel<T> model) {
		model.leftLeg.setPos(1.9F, 12.0F, 0.0F);
		model.rightLeg.setPos(-1.9F, 12.0F, 0.0F);
		model.head.setPos(0.0F, 0.0F, 0.0F);
		model.rightArm.z = 0.0F;
		model.rightArm.x = -5.0F;
		model.leftArm.z = 0.0F;
		model.leftArm.x = 5.0F;
		model.body.xRot = 0.0F;
		model.rightLeg.z = 0.1F;
		model.leftLeg.z = 0.1F;
		model.rightLeg.y = 12.0F;
		model.leftLeg.y = 12.0F;
		model.head.y = 0.0F;
		model.head.zRot = 0f;
		model.body.y = 0.0F;
		model.body.x = 0f;
		model.body.z = 0f;
		model.body.yRot = 0;
		model.body.zRot = 0;
		model.head.xScale = ModelPart.DEFAULT_SCALE;
		model.head.yScale = ModelPart.DEFAULT_SCALE;
		model.head.zScale = ModelPart.DEFAULT_SCALE;
		model.body.xScale = ModelPart.DEFAULT_SCALE;
		model.body.yScale = ModelPart.DEFAULT_SCALE;
		model.body.zScale = ModelPart.DEFAULT_SCALE;
		model.rightArm.xScale = ModelPart.DEFAULT_SCALE;
		model.rightArm.yScale = ModelPart.DEFAULT_SCALE;
		model.rightArm.zScale = ModelPart.DEFAULT_SCALE;
		model.leftArm.xScale = ModelPart.DEFAULT_SCALE;
		model.leftArm.yScale = ModelPart.DEFAULT_SCALE;
		model.leftArm.zScale = ModelPart.DEFAULT_SCALE;
		model.rightLeg.xScale = ModelPart.DEFAULT_SCALE;
		model.rightLeg.yScale = ModelPart.DEFAULT_SCALE;
		model.rightLeg.zScale = ModelPart.DEFAULT_SCALE;
		model.leftLeg.xScale = ModelPart.DEFAULT_SCALE;
		model.leftLeg.yScale = ModelPart.DEFAULT_SCALE;
		model.leftLeg.zScale = ModelPart.DEFAULT_SCALE;
	}

	private ModelPart getModelPart(PlayerModel<T> model, String boneName) {
		switch (boneName) {
			case "torso" :
				return model.body;
			case "head" :
				return model.head;
			case "right_arm" :
				return model.rightArm;
			case "left_arm" :
				return model.leftArm;
			case "right_leg" :
				return model.rightLeg;
			case "left_leg" :
				return model.leftLeg;
			default :
				return null;
		}
	}
}