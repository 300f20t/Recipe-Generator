package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;
import net.mcreator.recipe_generator.init.RecipeGeneratorModMenus;

import java.util.Calendar;

public class RecipeNameCreatorProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		String fileName = "";
		RecipeGeneratorModVariables.Recipe_name = (entity instanceof Player _entity0 && _entity0.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "recipe_name", "") : "";
		if ((RecipeGeneratorModVariables.Recipe_name).isEmpty()) {
			RecipeGeneratorModVariables.Recipe_name = "generated-" + new java.text.SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(Calendar.getInstance().getTime());
		}
		return RecipeGeneratorModVariables.Recipe_name;
	}
}