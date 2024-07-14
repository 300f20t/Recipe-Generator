package net.mcreator.justctgui.network;

import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.PacketBuffer;
import net.minecraft.entity.player.PlayerEntity;

import net.mcreator.justctgui.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.justctgui.procedures.Iteminslot9incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot8incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot7incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot6incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot5incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot4incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot3incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot2incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot1incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot0incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.JustCtguiMod;

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

	public CraftingtableCTGUISlotMessage(PacketBuffer buffer) {
		this.slotID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
		this.changeType = buffer.readInt();
		this.meta = buffer.readInt();
	}

	public static void buffer(CraftingtableCTGUISlotMessage message, PacketBuffer buffer) {
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
			PlayerEntity entity = context.getSender();
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

	public static void handleSlotAction(PlayerEntity entity, int slot, int changeType, int meta, int x, int y, int z) {
		World world = entity.world;
		HashMap guistate = CraftingtableCTGUIMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
		if (slot == 0 && changeType == 0) {

			Iteminslot0incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 1 && changeType == 0) {

			Iteminslot1incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 2 && changeType == 0) {

			Iteminslot2incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 3 && changeType == 0) {

			Iteminslot3incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 4 && changeType == 0) {

			Iteminslot4incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 5 && changeType == 0) {

			Iteminslot5incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 6 && changeType == 0) {

			Iteminslot6incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 7 && changeType == 0) {

			Iteminslot7incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 8 && changeType == 0) {

			Iteminslot8incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 9 && changeType == 0) {

			Iteminslot9incraftingtableCTGUIProcedure.execute(world, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		JustCtguiMod.addNetworkMessage(CraftingtableCTGUISlotMessage.class, CraftingtableCTGUISlotMessage::buffer, CraftingtableCTGUISlotMessage::new, CraftingtableCTGUISlotMessage::handler);
	}
}
