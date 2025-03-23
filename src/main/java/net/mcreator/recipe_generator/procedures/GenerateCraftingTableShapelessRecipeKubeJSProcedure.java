package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class GenerateCraftingTableShapelessRecipeKubeJSProcedure {
	public static void execute() {
		String preGeneratedRecipe = "";
		String recipeType = "";
		String recipeTypeFutures = "";
		preGeneratedRecipe = ((RecipeGeneratorModVariables.item_in_slot_0).equals("'minecraft:air'") ? "" : RecipeGeneratorModVariables.item_in_slot_0 + ",") + ""
				+ ((RecipeGeneratorModVariables.item_in_slot_1).equals("'minecraft:air'") ? "" : RecipeGeneratorModVariables.item_in_slot_1 + ",")
				+ ((RecipeGeneratorModVariables.item_in_slot_2).equals("'minecraft:air'") ? "" : RecipeGeneratorModVariables.item_in_slot_2 + ",")
				+ ((RecipeGeneratorModVariables.item_in_slot_3).equals("'minecraft:air'") ? "" : RecipeGeneratorModVariables.item_in_slot_3 + ",")
				+ ((RecipeGeneratorModVariables.item_in_slot_4).equals("'minecraft:air'") ? "" : RecipeGeneratorModVariables.item_in_slot_5 + ",")
				+ ((RecipeGeneratorModVariables.item_in_slot_6).equals("'minecraft:air'") ? "" : RecipeGeneratorModVariables.item_in_slot_6 + ",")
				+ ((RecipeGeneratorModVariables.item_in_slot_7).equals("'minecraft:air'") ? "" : RecipeGeneratorModVariables.item_in_slot_7 + ",")
				+ ((RecipeGeneratorModVariables.item_in_slot_8).equals("'minecraft:air'") ? "" : RecipeGeneratorModVariables.item_in_slot_8 + ",");
	}
}
