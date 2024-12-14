package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

public class GenerateBlastFurnaceRacipesProcedure {
	public static void execute(LevelAccessor world, HashMap guistate) {
		if (guistate == null)
			return;
		String preGeneratedRecipe = "";
		if ((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty()) {
			RecipeGeneratorModVariables.Generated_recipe = "blastFurnace.addRecipe(\"" + "no_name" + "\", " + preGeneratedRecipe;
		} else {
			RecipeGeneratorModVariables.Generated_recipe = "blastFurnace.addRecipe(\"" + "" + (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "") + "\", " + preGeneratedRecipe;
		}
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(RecipeGeneratorModVariables.Generated_recipe), false);
	}
}
