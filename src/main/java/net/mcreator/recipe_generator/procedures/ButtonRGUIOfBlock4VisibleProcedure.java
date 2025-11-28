package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class ButtonRGUIOfBlock4VisibleProcedure {
	public static boolean execute() {
		if (RecipeGeneratorModVariables.GUILabelsList.size() >= 4) {
			return true;
		}
		return false;
	}
}