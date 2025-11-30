package net.mcreator.recipe_generator.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import org.checkerframework.checker.units.qual.A;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.Minecraft;

import net.mcreator.recipe_generator.RecipeGeneratorModPlayerAnimationAPI;

import com.mojang.blaze3d.vertex.PoseStack;

@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> {
	private String master = null;
	private Player player = null;
	private Minecraft mc = Minecraft.getInstance();

	@Inject(method = "Lnet/minecraft/client/renderer/entity/layers/HumanoidArmorLayer;render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At("HEAD"))
	private void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
		if (master == null) {
			if (!RecipeGeneratorModPlayerAnimationAPI.animations.isEmpty())
				master = "recipe_generator";
			else
				return;
		}
		if (!master.equals("recipe_generator")) {
			return;
		}
		if (livingEntity instanceof Player player) {
			this.player = player;
		}
	}

	@Inject(method = "Lnet/minecraft/client/renderer/entity/layers/HumanoidArmorLayer;setPartVisibility(Lnet/minecraft/client/model/HumanoidModel;Lnet/minecraft/world/entity/EquipmentSlot;)V", at = @At("TAIL"))
	private void setPartVisibility(HumanoidModel model, EquipmentSlot slot, CallbackInfo ci) {
		if (master == null) {
			if (!RecipeGeneratorModPlayerAnimationAPI.animations.isEmpty())
				master = "recipe_generator";
			else
				return;
		}
		if (!master.equals("recipe_generator")) {
			return;
		}
		if (player == null)
			return;
		CompoundTag playerData = player.getPersistentData();
		if (player != null && player.getPersistentData().getBoolean("FirstPersonAnimation") && mc.options.getCameraType().isFirstPerson() && mc.player == player && mc.screen == null) {
			hideArmorParts(model);
			playerData.putInt("setNullRender", 5);
		} else if (playerData.contains("setNullRender")) {
			hideArmorParts(model);
			playerData.putInt("setNullRender", playerData.getInt("setNullRender") - 1);
			if (playerData.getInt("setNullRender") <= 0)
				playerData.remove("setNullRender");
		}
	}

	private void hideArmorParts(HumanoidModel armorModel) {
		armorModel.head.visible = false;
		armorModel.body.visible = false;
		armorModel.leftLeg.visible = false;
		armorModel.rightLeg.visible = false;
		armorModel.hat.visible = false;
	}
}