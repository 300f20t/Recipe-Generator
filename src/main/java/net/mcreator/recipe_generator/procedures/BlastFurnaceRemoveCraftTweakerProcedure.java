package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class BlastFurnaceRemoveCraftTweakerProcedure {
	public static void execute() {
		RecipeGeneratorModVariables.Generated_recipe = "#priority -10" + "\n" + "import crafttweaker.api.recipe.BlastFurnaceRecipeManager; " + "blastFurnace.remove(" + RecipeGeneratorModVariables.item_in_slot_0 + ");";
	}
}