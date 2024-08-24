package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class VerticalispressedProcedure {
	public static boolean execute() {
		if (("VERTICAL").equals(RecipeGeneratorModVariables.Mirror_axis)) {
			return true;
		}
		return false;
	}
}
