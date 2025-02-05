package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class NoneispressedProcedure {
	public static boolean execute() {
		if (("NONE").equals(RecipeGeneratorModVariables.Mirror_axis)) {
			return true;
		}
		return false;
	}
}
