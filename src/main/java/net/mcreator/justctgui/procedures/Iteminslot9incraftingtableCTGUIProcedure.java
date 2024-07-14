package net.mcreator.justctgui.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.IWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.justctgui.network.JustCtguiModVariables;
import net.mcreator.justctgui.JustCtguiMod;

import java.util.function.Supplier;
import java.util.Map;

public class Iteminslot9incraftingtableCTGUIProcedure {
	public static void execute(IWorld world, Entity entity) {
		if (entity == null)
			return;
		JustCtguiMod.queueServerWork(1, () -> {
			JustCtguiModVariables.item_in_slot_9_crafting_table = "<item:"
					+ (ForgeRegistries.ITEMS.getKey((entity instanceof PlayerEntity && ((PlayerEntity) entity).openContainer instanceof Supplier && ((Supplier) ((PlayerEntity) entity).openContainer).get() instanceof Map
							? ((Slot) ((Map) ((Supplier) ((PlayerEntity) entity).openContainer).get()).get(9)).getStack()
							: ItemStack.EMPTY).getItem()).toString())
					+ ">" + " * " + (new Object() {
						public int getAmount(int sltid) {
							if (entity instanceof PlayerEntity && ((PlayerEntity) entity).openContainer instanceof Supplier && ((Supplier) ((PlayerEntity) entity).openContainer).get() instanceof Map) {
								ItemStack stack = ((Slot) ((Map) ((Supplier) ((PlayerEntity) entity).openContainer).get()).get(sltid)).getStack();
								if (stack != null)
									return stack.getCount();
							}
							return 0;
						}
					}.getAmount(9));
		});
	}
}
