package net.mcreator.recipe_generator.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import org.checkerframework.checker.units.qual.g;

import net.minecraft.world.phys.Vec3;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.Minecraft;

import net.mcreator.recipe_generator.RecipeGeneratorModPlayerAnimationAPI;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.PoseStack;

@Mixin(PlayerRenderer.class)
public abstract class PlayerAnimationRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
	private String master = null;
	private Minecraft mc = Minecraft.getInstance();

	public PlayerAnimationRendererMixin(EntityRendererProvider.Context context, PlayerModel<AbstractClientPlayer> entityModel, float f) {
		super(context, entityModel, f);
	}

	@Inject(method = "render(Lnet/minecraft/client/player/AbstractClientPlayer;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V"))
	private void hideBonesInFirstPerson(AbstractClientPlayer entity, float f, float g, PoseStack poseStack, MultiBufferSource bufferSource, int light, CallbackInfo ci) {
		if (master == null) {
			if (!RecipeGeneratorModPlayerAnimationAPI.animations.isEmpty())
				master = "recipe_generator";
			else
				return;
		}
		if (!master.equals("recipe_generator")) {
			return;
		}
		if (entity.getPersistentData().getBoolean("FirstPersonAnimation") && mc.options.getCameraType().isFirstPerson() && entity == mc.player && mc.screen == null) {
			this.model.head.visible = false;
			this.model.body.visible = false;
			this.model.leftLeg.visible = false;
			this.model.rightLeg.visible = false;
			this.model.rightArm.visible = false;
			this.model.leftArm.visible = false;
			this.model.hat.visible = false;
			this.model.leftSleeve.visible = false;
			this.model.rightSleeve.visible = false;
			this.model.leftPants.visible = false;
			this.model.rightPants.visible = false;
			this.model.jacket.visible = false;
			this.model.rightArm.visible = true;
			this.model.leftArm.visible = true;
		}
	}

	@Inject(method = "Lnet/minecraft/client/renderer/entity/player/PlayerRenderer;setupRotations(Lnet/minecraft/client/player/AbstractClientPlayer;Lcom/mojang/blaze3d/vertex/PoseStack;FFFF)V", at = @At("RETURN"))
	private void setupRotations(AbstractClientPlayer player, PoseStack poseStack, float f, float bodyYaw, float deltaTick, float g, CallbackInfo ci) {
		if (master == null) {
			if (!RecipeGeneratorModPlayerAnimationAPI.animations.isEmpty())
				master = "recipe_generator";
			else
				return;
		}
		if (!master.equals("recipe_generator")) {
			return;
		}
		RecipeGeneratorModPlayerAnimationAPI.PlayerAnimation animation = RecipeGeneratorModPlayerAnimationAPI.active_animations.get(player);
		if (animation == null)
			return;
		RecipeGeneratorModPlayerAnimationAPI.PlayerBone bone = animation.bones.get("body");
		if (bone == null)
			return;
		boolean firstPerson = player.getPersistentData().getBoolean("FirstPersonAnimation") && mc.options.getCameraType().isFirstPerson() && player == mc.player && mc.screen == null;
		float animationProgress = player.getPersistentData().getFloat("PlayerAnimationProgress");
		Vec3 scale = RecipeGeneratorModPlayerAnimationAPI.PlayerBone.interpolate(bone.scales, animationProgress);
		if (scale != null) {
			poseStack.scale((float) scale.x, (float) scale.y, (float) scale.z);
		}
		Vec3 position = RecipeGeneratorModPlayerAnimationAPI.PlayerBone.interpolate(bone.positions, animationProgress);
		if (position != null) {
			if (!firstPerson)
				poseStack.translate((float) -position.x * 0.0625f, (float) (position.y * 0.0625f), (float) position.z * 0.0625f);
		}
		Vec3 rotation = RecipeGeneratorModPlayerAnimationAPI.PlayerBone.interpolate(bone.rotations, animationProgress);
		if (rotation != null) {
			if (!firstPerson)
				poseStack.mulPose(Axis.ZP.rotationDegrees((float) rotation.z));
			poseStack.mulPose(Axis.YP.rotationDegrees((float) -rotation.y));
			if (!firstPerson)
				poseStack.mulPose(Axis.XP.rotationDegrees((float) -rotation.x));
		}
	}
}