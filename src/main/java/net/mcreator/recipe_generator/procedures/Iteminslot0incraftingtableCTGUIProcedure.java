package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class Iteminslot0incraftingtableCTGUIProcedure {
	public static void execute() {
		if (true) {
			RecipeGeneratorModVariables.Generated_recipe = CraftTweakerFormatProcedure.execute();
		} else if (true) {
			RecipeGeneratorModVariables.Generated_recipe = KubeJSFormatProcedure.execute();
		}
	}
}
