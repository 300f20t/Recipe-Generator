package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

public class GenerateCraftingTableRecipeProcedure {
	public static void execute(LevelAccessor world, HashMap guistate) {
		if (guistate == null)
			return;
		if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("CraftTweaker")) {
			GenerateCraftingTableRecipeCraftTweakerProcedure.execute(guistate);
		} else if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("KubeJS")) {
			GenerateCraftingTableRecipeKubeJSProcedure.execute();
		}
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(RecipeGeneratorModVariables.Generated_recipe), false);
	}
}
