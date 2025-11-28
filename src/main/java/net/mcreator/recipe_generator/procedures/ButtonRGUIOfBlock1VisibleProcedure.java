package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class ButtonRGUIOfBlock1VisibleProcedure {
	public static boolean execute() {
		if (RecipeGeneratorModVariables.GUILabelsList.size() >= 1) {
			return true;
		}
		return false;
	}
}