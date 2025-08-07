package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

public class AvaritaCraftingTableAddShapedCraftTweakerProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		String recipeType = "";
		String recipeTypeFutures = "";
		recipeType = "mods.avaritia.CraftingTable.addShaped(\"";
		RecipeGeneratorModVariables.Generated_recipe = recipeType + ""
				+ ((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty()
						? RecipeNameCreatorProcedure.execute(guistate)
						: (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : ""))
				+ "\", " + "4, " + (entity.getCapability(RecipeGeneratorModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RecipeGeneratorModVariables.PlayerVariables())).preGeneratedRecipe;
	}
}
