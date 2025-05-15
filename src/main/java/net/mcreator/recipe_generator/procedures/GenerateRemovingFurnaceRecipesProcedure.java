package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class GenerateRemovingFurnaceRecipesProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		RecipeGeneratorModVariables.Generated_recipe = "furnace.remove(" + RecipeGeneratorModVariables.item_in_slot_0 + ");";
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(RecipeGeneratorModVariables.Generated_recipe), false);
	}
}
