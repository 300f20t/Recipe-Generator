package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class GenerateBlastFurnaceRemovingRecipesCraftTweakerProcedure {
	public static void execute() {
		RecipeGeneratorModVariables.Generated_recipe = "import crafttweaker.api.recipe.BlastFurnaceRecipeManager; " + "blastFurnace.remove(" + RecipeGeneratorModVariables.item_in_slot_0 + ");";
	}
}
