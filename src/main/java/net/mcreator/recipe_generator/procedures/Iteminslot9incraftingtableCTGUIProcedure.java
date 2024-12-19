package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.function.Supplier;
import java.util.Map;

public class Iteminslot9incraftingtableCTGUIProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		RecipeGeneratorModVariables.item_in_slot_9_count = new Object() {
			public int getAmount(int sltid) {
				if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
					ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
					if (stack != null)
						return stack.getCount();
				}
				return 0;
			}
		}.getAmount(9);
	}
}
