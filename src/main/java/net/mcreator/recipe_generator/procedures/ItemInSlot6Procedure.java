package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.function.Supplier;
import java.util.Map;

public class ItemInSlot6Procedure {
	public static void execute(LevelAccessor world, Entity entity, ItemStack inputItem) {
		if (entity == null)
			return;
		ItemsFormatProcedure.execute(world, entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(6)).getItem() : ItemStack.EMPTY);
		RecipeGeneratorModVariables.item_in_slot_6 = ItemsFormatProcedure.execute(world, inputItem);
	}
}
