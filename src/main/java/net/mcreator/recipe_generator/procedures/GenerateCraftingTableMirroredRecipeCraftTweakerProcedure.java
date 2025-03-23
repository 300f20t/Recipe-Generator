package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

public class GenerateCraftingTableMirroredRecipeCraftTweakerProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		String recipeType = "";
		String recipeTypeFutures = "";
		recipeType = "craftingTable.addShapedMirrored(\"";
		recipeTypeFutures = "MirrorAxis." + RecipeGeneratorModVariables.Mirror_axis + ", ";
		RecipeGeneratorModVariables.Generated_recipe = "import crafttweaker.api.recipe.MirrorAxis;" + recipeType
				+ ((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty()
						? RecipeNameCreatorProcedure.execute(guistate)
						: (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : ""))
				+ "\", " + recipeTypeFutures + entity.getData(RecipeGeneratorModVariables.PLAYER_VARIABLES).preGeneratedRecipe;
	}
}
