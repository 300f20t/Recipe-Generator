package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class ButtonRGUIOfBlock3VisibleProcedure {
	public static boolean execute() {
		if (RecipeGeneratorModVariables.GUILabelsList.size() >= 3) {
			return true;
		}
		return false;
	}
}