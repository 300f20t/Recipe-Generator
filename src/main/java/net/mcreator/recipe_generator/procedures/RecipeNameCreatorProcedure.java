package net.mcreator.recipe_generator.procedures;

import net.minecraft.client.gui.components.EditBox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;
import java.util.Calendar;

public class RecipeNameCreatorProcedure {
	public static String execute(HashMap guistate) {
		if (guistate == null)
			return "";
		String fileName = "";
		RecipeGeneratorModVariables.Recipe_name = guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "";
		if ((RecipeGeneratorModVariables.Recipe_name).isEmpty()) {
			RecipeGeneratorModVariables.Recipe_name = "generated " + new java.text.SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(Calendar.getInstance().getTime());
		}
		return RecipeGeneratorModVariables.Recipe_name;
	}
}
