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

public class Iteminslot5incraftingtableCTGUIProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		JustCtguiMod.queueServerWork(1, () -> {
			JustCtguiModVariables.item_in_slot_5_crafting_table = "<item:" + (ForgeRegistries.ITEMS
					.getKey((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(5)).getItem() : ItemStack.EMPTY).getItem()).toString()) + ">";
		});
	}
}
