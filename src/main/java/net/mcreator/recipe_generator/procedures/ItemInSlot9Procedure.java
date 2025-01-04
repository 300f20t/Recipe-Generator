package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class ItemInSlot9Procedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		new Object() {
			private int ticks = 0;

			public void startDelay(LevelAccessor world) {
				ServerTickEvents.END_SERVER_TICK.register((server) -> {
					this.ticks++;
					if (this.ticks == 1) {
						RecipeGeneratorModVariables.item_in_slot_9 = ItemsFormatProcedure.execute(world, inputItem);
						RecipeGeneratorModVariables.item_in_slot_9_count = (entity instanceof ServerPlayer _plrSlotItem ? _plrSlotItem.containerMenu.getSlot(9).getItem() : ItemStack.EMPTY).getCount();
						return;
					}
				});
			}
		}.startDelay(world);
	}
}
