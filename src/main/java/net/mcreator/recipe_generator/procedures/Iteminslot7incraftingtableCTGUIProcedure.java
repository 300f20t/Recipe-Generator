package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.function.Supplier;
import java.util.Map;

public class Iteminslot7incraftingtableCTGUIProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		RecipeGeneratorModVariables.item_in_slot_7 = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(7)).getItem() : ItemStack.EMPTY);
	}
}
