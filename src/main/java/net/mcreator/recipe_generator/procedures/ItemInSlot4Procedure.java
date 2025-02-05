package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class ItemInSlot4Procedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		new Object() {
			private int ticks = 0;

			public void startDelay(LevelAccessor world) {
				ServerTickEvents.END_SERVER_TICK.register((server) -> {
					this.ticks++;
					if (this.ticks == 1) {
						if ((RecipeGeneratorModVariables.selectedMethod).equals("CraftTweaker")) {
							RecipeGeneratorModVariables.item_in_slot_4 = "<item:"
									+ (BuiltInRegistries.ITEM.getKey((entity instanceof ServerPlayer _plrSlotItem ? _plrSlotItem.containerMenu.getSlot(4).getItem() : ItemStack.EMPTY).getItem()).toString()) + ">";
						} else if ((RecipeGeneratorModVariables.selectedMethod).equals("KubeJS")) {
							RecipeGeneratorModVariables.item_in_slot_4 = "'" + (BuiltInRegistries.ITEM.getKey((entity instanceof ServerPlayer _plrSlotItem ? _plrSlotItem.containerMenu.getSlot(4).getItem() : ItemStack.EMPTY).getItem()).toString())
									+ "'";
						}
						return;
					}
				});
			}
		}.startDelay(world);
	}
}
