package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class DiagonalispressedProcedure {
	public static boolean execute() {
		if (("DIAGONAL").equals(RecipeGeneratorModVariables.Mirror_axis)) {
			return true;
		}
		return false;
	}
}
