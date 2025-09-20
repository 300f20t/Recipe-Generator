package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class TextLabelOfBlockRGUI3Procedure {
	public static String execute() {
		if (RecipeGeneratorModVariables.GUILabelsList.size() >= 3) {
			return RecipeGeneratorModVariables.GUILabelsList.get(2) instanceof String _s ? _s : "";
		}
		return "";
	}
}