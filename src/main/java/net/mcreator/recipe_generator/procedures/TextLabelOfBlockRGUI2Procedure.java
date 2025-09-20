package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class TextLabelOfBlockRGUI2Procedure {
	public static String execute() {
		if (RecipeGeneratorModVariables.GUILabelsList.size() >= 2) {
			return RecipeGeneratorModVariables.GUILabelsList.get(1) instanceof String _s ? _s : "";
		}
		return "";
	}
}