
package net.mcreator.justctgui.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.justctgui.world.inventory.FurnaceCTGUIMenu;
import net.mcreator.justctgui.procedures.Iteminslot1incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.procedures.Iteminslot0incraftingtableCTGUIProcedure;
import net.mcreator.justctgui.JustCtguiMod;

import java.util.HashMap;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record FurnaceCTGUISlotMessage(int slotID, int x, int y, int z, int changeType, int meta) implements CustomPacketPayload {

	public static final Type<FurnaceCTGUISlotMessage> TYPE = new Type<>(new ResourceLocation(JustCtguiMod.MODID, "furnace_ctgui_slots"));
	public static final StreamCodec<RegistryFriendlyByteBuf, FurnaceCTGUISlotMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, FurnaceCTGUISlotMessage message) -> {
		buffer.writeInt(message.slotID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		buffer.writeInt(message.changeType);
		buffer.writeInt(message.meta);
	}, (RegistryFriendlyByteBuf buffer) -> new FurnaceCTGUISlotMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<FurnaceCTGUISlotMessage> type() {
		return TYPE;
	}

	public static void handleData(final FurnaceCTGUISlotMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> {
				Player entity = context.player();
				int slotID = message.slotID;
				int changeType = message.changeType;
				int meta = message.meta;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleSlotAction(entity, slotID, changeType, meta, x, y, z);
			}).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleSlotAction(Player entity, int slot, int changeType, int meta, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = FurnaceCTGUIMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (slot == 0 && changeType == 0) {

			Iteminslot0incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 1 && changeType == 0) {

			Iteminslot1incraftingtableCTGUIProcedure.execute(world, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		JustCtguiMod.addNetworkMessage(FurnaceCTGUISlotMessage.TYPE, FurnaceCTGUISlotMessage.STREAM_CODEC, FurnaceCTGUISlotMessage::handleData);
	}
}
