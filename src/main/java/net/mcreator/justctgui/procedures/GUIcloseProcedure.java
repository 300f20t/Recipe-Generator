package net.mcreator.justctgui.procedures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

public class GUIcloseProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).closeScreen();
	}
}
