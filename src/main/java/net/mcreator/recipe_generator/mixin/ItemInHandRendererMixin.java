package net.mcreator.recipe_generator.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.Minecraft;

import net.mcreator.recipe_generator.RecipeGeneratorModPlayerAnimationAPI;

import com.mojang.blaze3d.vertex.PoseStack;

@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {
	private String master = null;
	private Minecraft mc = Minecraft.getInstance();
	private EntityRenderDispatcher dispatcher = null;

	@Inject(method = "renderHandsWithItems", at = @At("HEAD"), cancellable = true)
	private void renderHandsWithItems(float f, PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, LocalPlayer localPlayer, int i, CallbackInfo ci) {
		if (master == null) {
			if (!RecipeGeneratorModPlayerAnimationAPI.animations.isEmpty())
				master = "recipe_generator";
			else
				return;
		}
		if (!master.equals("recipe_generator"))
			return;
		if (localPlayer instanceof Player player && mc.player == player && mc.screen == null) {
			if (dispatcher == null)
				dispatcher = mc.getEntityRenderDispatcher();
			CompoundTag playerData = player.getPersistentData();
			// Hack to make animations progress when in first person without first person mode enabled
			if (!playerData.getString("PlayerCurrentAnimation").isEmpty() && (!playerData.getBoolean("FirstPersonAnimation") || playerData.getBoolean("ResetPlayerAnimation"))) {
				PlayerModel model = ((PlayerRenderer) dispatcher.getRenderer((AbstractClientPlayer) player)).getModel();
				model.setupAnim((AbstractClientPlayer) player, 0, 0, player.tickCount + f, 0, 0);
			}
			if (playerData.getBoolean("FirstPersonAnimation"))
				ci.cancel();
		}
	}
}