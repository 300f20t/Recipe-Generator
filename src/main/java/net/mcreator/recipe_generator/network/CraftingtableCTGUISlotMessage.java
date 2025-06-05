
package net.mcreator.recipe_generator.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.recipe_generator.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.recipe_generator.procedures.ItemInSlot9Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot8Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot7Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot6Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot5Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot4Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot3Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot2Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot1Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot0Procedure;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CraftingtableCTGUISlotMessage {
	private final int slotID, x, y, z, changeType, meta;

	public CraftingtableCTGUISlotMessage(int slotID, int x, int y, int z, int changeType, int meta) {
		this.slotID = slotID;
		this.x = x;
		this.y = y;
		this.z = z;
		this.changeType = changeType;
		this.meta = meta;
	}

	public CraftingtableCTGUISlotMessage(FriendlyByteBuf buffer) {
		this.slotID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
		this.changeType = buffer.readInt();
		this.meta = buffer.readInt();
	}

	public static void buffer(CraftingtableCTGUISlotMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.slotID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		buffer.writeInt(message.changeType);
		buffer.writeInt(message.meta);
	}

	public static void handler(CraftingtableCTGUISlotMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int slotID = message.slotID;
			int changeType = message.changeType;
			int meta = message.meta;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleSlotAction(entity, slotID, changeType, meta, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleSlotAction(Player entity, int slot, int changeType, int meta, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = CraftingtableCTGUIMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (slot == 0 && changeType == 0) {

			ItemInSlot0Procedure.execute(world, entity);
		}
		if (slot == 1 && changeType == 0) {

			ItemInSlot1Procedure.execute(world, entity);
		}
		if (slot == 2 && changeType == 0) {

			ItemInSlot2Procedure.execute(world, entity);
		}
		if (slot == 3 && changeType == 0) {

			ItemInSlot3Procedure.execute(world, entity);
		}
		if (slot == 4 && changeType == 0) {

			ItemInSlot4Procedure.execute(world, entity);
		}
		if (slot == 5 && changeType == 0) {

			ItemInSlot5Procedure.execute(world, entity);
		}
		if (slot == 6 && changeType == 0) {

			ItemInSlot6Procedure.execute(world, entity);
		}
		if (slot == 7 && changeType == 0) {

			ItemInSlot7Procedure.execute(world, entity);
		}
		if (slot == 8 && changeType == 0) {

			ItemInSlot8Procedure.execute(world, entity);
		}
		if (slot == 9 && changeType == 0) {

			ItemInSlot9Procedure.execute(world, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		RecipeGeneratorMod.addNetworkMessage(CraftingtableCTGUISlotMessage.class, CraftingtableCTGUISlotMessage::buffer, CraftingtableCTGUISlotMessage::new, CraftingtableCTGUISlotMessage::handler);
	}
}
