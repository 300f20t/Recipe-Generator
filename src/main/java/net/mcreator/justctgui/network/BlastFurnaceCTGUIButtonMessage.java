
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

import net.mcreator.justctgui.world.inventory.BlastFurnaceCTGUIMenu;
import net.mcreator.justctgui.procedures.ScriptswriterProcedure;
import net.mcreator.justctgui.procedures.ReloadCommandProcedure;
import net.mcreator.justctgui.procedures.GenerateBlastFurnaceRacipesProcedure;
import net.mcreator.justctgui.procedures.GUIcloseProcedure;
import net.mcreator.justctgui.JustCtguiMod;

import java.util.HashMap;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record BlastFurnaceCTGUIButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<BlastFurnaceCTGUIButtonMessage> TYPE = new Type<>(new ResourceLocation(JustCtguiMod.MODID, "blast_furnace_ctgui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, BlastFurnaceCTGUIButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, BlastFurnaceCTGUIButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new BlastFurnaceCTGUIButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<BlastFurnaceCTGUIButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final BlastFurnaceCTGUIButtonMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> {
				Player entity = context.player();
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleButtonAction(entity, buttonID, x, y, z);
			}).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = BlastFurnaceCTGUIMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			GenerateBlastFurnaceRacipesProcedure.execute(world, guistate);
		}
		if (buttonID == 1) {

			ScriptswriterProcedure.execute(entity, guistate);
		}
		if (buttonID == 2) {

			GUIcloseProcedure.execute(entity);
		}
		if (buttonID == 3) {

			ReloadCommandProcedure.execute(world, x, y, z);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		JustCtguiMod.addNetworkMessage(BlastFurnaceCTGUIButtonMessage.TYPE, BlastFurnaceCTGUIButtonMessage.STREAM_CODEC, BlastFurnaceCTGUIButtonMessage::handleData);
	}
}
