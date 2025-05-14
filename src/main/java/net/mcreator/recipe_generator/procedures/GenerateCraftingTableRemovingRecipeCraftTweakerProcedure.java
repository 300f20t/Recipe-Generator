package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class GenerateCraftingTableRemovingRecipeCraftTweakerProcedure {
	public static void execute() {
		RecipeGeneratorModVariables.Generated_recipe = "craftingTable.remove(" + RecipeGeneratorModVariables.item_in_slot_0 + ");";
	}
}
