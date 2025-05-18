package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class FurnaceRemoveCraftTweakerProcedure {
	public static void execute() {
		RecipeGeneratorModVariables.Generated_recipe = "furnace.remove(" + RecipeGeneratorModVariables.item_in_slot_0 + ");";
	}
}
