package net.mcreator.recipe_generator.procedures;

import net.minecraft.client.gui.components.Checkbox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

public class RecipeshapeProcedure {
	public static void execute(HashMap guistate) {
		if (guistate == null)
			return;
		if (guistate.containsKey("checkbox:Is_mirrored") && ((Checkbox) guistate.get("checkbox:Is_mirrored")).selected()) {
			RecipeGeneratorModVariables.Is_mirrored = true;
		} else {
			RecipeGeneratorModVariables.Is_mirrored = false;
		}
		if (guistate.containsKey("checkbox:Is_shapeless") && ((Checkbox) guistate.get("checkbox:Is_shapeless")).selected()) {
			RecipeGeneratorModVariables.Is_shapeless = true;
		} else {
			RecipeGeneratorModVariables.Is_shapeless = false;
		}
	}
}
