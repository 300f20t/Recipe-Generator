package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class CampFireRemoveCraftTweakerProcedure {
	public static void execute() {
		RecipeGeneratorModVariables.Generated_recipe = "import crafttweaker.api.recipe.CampFireRecipeManager;" + "campfire.remove(" + RecipeGeneratorModVariables.item_in_slot_0 + ");";
	}
}
