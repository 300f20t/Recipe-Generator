package net.mcreator.recipe_generator.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.level.LevelReader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.vertex.PoseStack;

@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRenderDispatcherMixin {
	private static Minecraft mc = Minecraft.getInstance();

	@Inject(method = "renderShadow", at = @At("HEAD"), cancellable = true)
	private static void renderShadow(PoseStack poseStack, MultiBufferSource bufferSource, Entity entity, float opacity, float tickDelta, LevelReader world, float radius, CallbackInfo ci) {
		if (entity instanceof Player player && mc.options.getCameraType().isFirstPerson() && player == mc.player && mc.screen == null) {
			if (player.getPersistentData().getBoolean("FirstPersonAnimation"))
				ci.cancel();
		}
	}
}