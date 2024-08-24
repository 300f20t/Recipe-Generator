package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class GenerateRemovingFurnaceRecipesProcedure {
	public static void execute(LevelAccessor world) {
		RecipeGeneratorModVariables.Generated_recipe = "import crafttweaker.api.recipe.FurnaceRecipeManager; " + "furnace.remove(" + RecipeGeneratorModVariables.item_in_slot_0_crafting_table + ");";
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(RecipeGeneratorModVariables.Generated_recipe), false);
	}
}
