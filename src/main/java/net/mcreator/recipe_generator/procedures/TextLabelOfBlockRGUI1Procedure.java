package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class TextLabelOfBlockRGUI1Procedure {
	public static String execute() {
		if (RecipeGeneratorModVariables.GUILabelsList.size() >= 1) {
			return RecipeGeneratorModVariables.GUILabelsList.get(0) instanceof String _s ? _s : "";
		}
		return "";
	}
}