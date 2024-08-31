package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class ChangeSelectedGeneratingMethodToCraftTweakerProcedure {
	public static void execute() {
		RecipeGeneratorModVariables.selectedMethod = "CraftTweaker";
	}
}
