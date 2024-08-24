package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class ATTENTIONProcedure {
	public static boolean execute() {
		if (RecipeGeneratorModVariables.Is_mirrored) {
			if (RecipeGeneratorModVariables.Is_shapeless) {
				return true;
			}
		}
		return false;
	}
}
