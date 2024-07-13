package net.mcreator.justctgui.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.justctgui.network.JustCtguiModVariables;
import net.mcreator.justctgui.JustCtguiMod;

import java.util.function.Supplier;
import java.util.Map;

public class Iteminslot9incraftingtableCTGUIProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		JustCtguiMod.queueServerWork(1, () -> {
			JustCtguiModVariables.item_in_slot_9_crafting_table = "<item:" + (ForgeRegistries.ITEMS
					.getKey((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(9)).getItem() : ItemStack.EMPTY).getItem()).toString()) + ">" + " * "
					+ (new Object() {
						public int getAmount(int sltid) {
							if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
								ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
								if (stack != null)
									return stack.getCount();
							}
							return 0;
						}
					}.getAmount(9));
		});
	}
}
