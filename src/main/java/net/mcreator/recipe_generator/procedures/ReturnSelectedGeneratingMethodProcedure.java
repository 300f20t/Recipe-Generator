package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class ReturnSelectedGeneratingMethodProcedure {
	public static String execute() {
		return RecipeGeneratorModVariables.selectedMethod;
	}
}
