package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class ChangeSelectedGeneratingMethodToKubeJSProcedure {
	public static void execute() {
		RecipeGeneratorModVariables.selectedMethod = "KubeJS";
	}
}
