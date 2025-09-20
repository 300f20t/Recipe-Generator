package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class TextLabelOfBlockRGUI4Procedure {
	public static String execute() {
		if (RecipeGeneratorModVariables.GUILabelsList.size() >= 4) {
			return RecipeGeneratorModVariables.GUILabelsList.get(3) instanceof String _s ? _s : "";
		}
		return "";
	}
}