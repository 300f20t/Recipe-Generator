package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class CraftingTableAdd2x2RecipeProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		{
			RecipeGeneratorModVariables.PlayerVariables _vars = entity.getData(RecipeGeneratorModVariables.PLAYER_VARIABLES);
			_vars.preGeneratedRecipe = RecipeGeneratorModVariables.item_in_slot_4 + " * " + new java.text.DecimalFormat("##").format(RecipeGeneratorModVariables.item_in_slot_4_count) + ", [" + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_0
					+ ", " + RecipeGeneratorModVariables.item_in_slot_1 + "], " + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_2 + ", " + RecipeGeneratorModVariables.item_in_slot_3 + "]]);";
			_vars.markSyncDirty();
		}
		if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("CraftTweaker")) {
			CraftingTableAdd2x2RecipeCraftTweakerProcedure.execute(entity);
		} else if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("KubeJS")) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("WIP"), false);
		}
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(RecipeGeneratorModVariables.Generated_recipe), false);
	}
}