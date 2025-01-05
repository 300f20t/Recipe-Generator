package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

public class GUIcloseProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof ServerPlayer) {
			ServerPlayer player = (ServerPlayer) entity;
			player.closeContainer();
		}
	}
}
