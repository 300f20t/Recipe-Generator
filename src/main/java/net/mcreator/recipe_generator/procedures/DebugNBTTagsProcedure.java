package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;

public class DebugNBTTagsProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("" + (new Object() {
				public double getValue() {
					CompoundTag dataIndex = new CompoundTag();
					entity.saveWithoutId(dataIndex);
					return dataIndex.getCompound("Inventory").getDouble("Slot");
				}
			}.getValue()))), false);
	}
}
