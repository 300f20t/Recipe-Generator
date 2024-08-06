
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

import net.mcreator.justctgui.world.inventory.FurnaceRemovingCTGUIMenu;
import net.mcreator.justctgui.procedures.ScriptswriterProcedure;
import net.mcreator.justctgui.procedures.ReloadCommandProcedure;
import net.mcreator.justctgui.procedures.GenerateRemovingFurnaceRecipesProcedure;
import net.mcreator.justctgui.procedures.GUIcloseProcedure;
import net.mcreator.justctgui.JustCtguiMod;

import java.util.HashMap;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record FurnaceRemovingCTGUIButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<FurnaceRemovingCTGUIButtonMessage> TYPE = new Type<>(new ResourceLocation(JustCtguiMod.MODID, "furnace_removing_ctgui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, FurnaceRemovingCTGUIButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, FurnaceRemovingCTGUIButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new FurnaceRemovingCTGUIButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<FurnaceRemovingCTGUIButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final FurnaceRemovingCTGUIButtonMessage message, final IPayloadContext context) {
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
		HashMap guistate = FurnaceRemovingCTGUIMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			GenerateRemovingFurnaceRecipesProcedure.execute(world);
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
		JustCtguiMod.addNetworkMessage(FurnaceRemovingCTGUIButtonMessage.TYPE, FurnaceRemovingCTGUIButtonMessage.STREAM_CODEC, FurnaceRemovingCTGUIButtonMessage::handleData);
	}
}
