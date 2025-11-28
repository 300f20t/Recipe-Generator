package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class ButtonRGUIOfBlock2VisibleProcedure {
	public static boolean execute() {
		if (RecipeGeneratorModVariables.GUILabelsList.size() >= 2) {
			return true;
		}
		return false;
	}
}