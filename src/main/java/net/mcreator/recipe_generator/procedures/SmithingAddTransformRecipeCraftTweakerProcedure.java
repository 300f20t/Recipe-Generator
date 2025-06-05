package net.mcreator.recipe_generator.procedures;

import net.minecraft.client.gui.components.EditBox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

public class SmithingAddTransformRecipeCraftTweakerProcedure {
	public static void execute(HashMap guistate) {
		if (guistate == null)
			return;
		String preGeneratedRecipe = "";
		preGeneratedRecipe = RecipeGeneratorModVariables.item_in_slot_3 + ", " + RecipeGeneratorModVariables.item_in_slot_0 + ", " + RecipeGeneratorModVariables.item_in_slot_1 + ", " + RecipeGeneratorModVariables.item_in_slot_2 + ");";
		RecipeGeneratorModVariables.Generated_recipe = "import crafttweaker.api.recipe.SmithingRecipeManager;" + "\n" + "import crafttweaker.api.item.IItemStack;" + "\n" + "smithing.addTransformRecipe(\""
				+ ((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty()
						? RecipeNameCreatorProcedure.execute(guistate)
						: (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : ""))
				+ "\", " + preGeneratedRecipe;
	}
}
