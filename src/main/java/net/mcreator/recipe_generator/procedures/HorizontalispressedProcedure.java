package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class HorizontalispressedProcedure {
	public static boolean execute() {
		if (("HORIZONTAL").equals(RecipeGeneratorModVariables.Mirror_axis)) {
			return true;
		}
		return false;
	}
}
