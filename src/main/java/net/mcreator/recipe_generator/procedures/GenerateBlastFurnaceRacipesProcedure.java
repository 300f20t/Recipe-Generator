package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

public class GenerateBlastFurnaceRacipesProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		String preGeneratedRecipe = "";
		if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("CraftTweaker")) {
			BlastFurnaceAddRecipeCraftTweakerProcedure.execute(guistate);
		} else if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("KubeJS")) {
			if (!world.isClientSide() && world.getServer() != null)
				world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("WIP"), false);
		}
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(RecipeGeneratorModVariables.Generated_recipe), false);
	}
}
