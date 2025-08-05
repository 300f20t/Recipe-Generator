package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.recipe_generator.init.RecipeGeneratorModMenus;

import java.util.Calendar;

public class FileNameCreatorProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		String fileName = "";
		fileName = (entity instanceof Player _entity0 && _entity0.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "file_name", "") : "";
		if ((fileName).isEmpty()) {
			fileName = "generated " + new java.text.SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(Calendar.getInstance().getTime());
		}
		return fileName;
	}
}