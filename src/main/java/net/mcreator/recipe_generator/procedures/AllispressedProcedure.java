package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class AllispressedProcedure {
	public static boolean execute() {
		if (("ALL").equals(RecipeGeneratorModVariables.Mirror_axis)) {
			return true;
		}
		return false;
	}
}
