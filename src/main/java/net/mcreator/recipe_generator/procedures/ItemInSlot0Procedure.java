package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;
import net.mcreator.recipe_generator.init.RecipeGeneratorModMenus;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

public class ItemInSlot0Procedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		RecipeGeneratorMod.queueServerWork(1, () -> {
			RecipeGeneratorModVariables.item_in_slot_0 = ItemsFormatProcedure.execute(world,
					entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu0 ? _menu0.getSlots().get(0).getItem() : ItemStack.EMPTY);
		});
	}
}