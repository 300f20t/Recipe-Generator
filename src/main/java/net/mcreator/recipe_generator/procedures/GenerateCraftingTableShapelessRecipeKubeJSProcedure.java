package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class GenerateCraftingTableShapelessRecipeKubeJSProcedure {
	public static void execute() {
		String preGeneratedRecipe = "";
		String recipeType = "";
		String recipeTypeFutures = "";
		double i = 0;
		preGeneratedRecipe = "\n" + (RecipeGeneratorModVariables.item_in_slot_0 + "," + "\n") + (RecipeGeneratorModVariables.item_in_slot_1 + "," + "\n") + (RecipeGeneratorModVariables.item_in_slot_2 + "," + "\n")
				+ (RecipeGeneratorModVariables.item_in_slot_3 + "," + "\n") + (RecipeGeneratorModVariables.item_in_slot_4 + "," + "\n") + (RecipeGeneratorModVariables.item_in_slot_5 + "," + "\n")
				+ (RecipeGeneratorModVariables.item_in_slot_6 + "," + "\n") + (RecipeGeneratorModVariables.item_in_slot_7 + "," + "\n") + (RecipeGeneratorModVariables.item_in_slot_8 + "," + "\n");
		RecipeGeneratorModVariables.Generated_recipe = "ServerEvents.recipes(event => {" + "event.shapeless(" + "\n" + " Item.of(" + RecipeGeneratorModVariables.item_in_slot_9 + ", "
				+ new java.text.DecimalFormat("##").format(RecipeGeneratorModVariables.item_in_slot_9_count) + ")," + "\n" + " [" + "\n" + " ]," + "\n" + " {" + preGeneratedRecipe + "})})";
	}
}
