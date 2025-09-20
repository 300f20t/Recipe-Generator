package net.mcreator.recipe_generator.network;

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

import net.mcreator.recipe_generator.procedures.ButtonRGUIOfBlock4Procedure;
import net.mcreator.recipe_generator.procedures.ButtonRGUIOfBlock3Procedure;
import net.mcreator.recipe_generator.procedures.ButtonRGUIOfBlock2Procedure;
import net.mcreator.recipe_generator.procedures.ButtonRGUIOfBlock1Procedure;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record CoosingRGUIOfBlockGUIButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<CoosingRGUIOfBlockGUIButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(RecipeGeneratorMod.MODID, "coosing_rgui_of_block_gui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, CoosingRGUIOfBlockGUIButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, CoosingRGUIOfBlockGUIButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new CoosingRGUIOfBlockGUIButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<CoosingRGUIOfBlockGUIButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final CoosingRGUIOfBlockGUIButtonMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> handleButtonAction(context.player(), message.buttonID, message.x, message.y, message.z)).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			ButtonRGUIOfBlock1Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			ButtonRGUIOfBlock2Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			ButtonRGUIOfBlock3Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 3) {

			ButtonRGUIOfBlock4Procedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		RecipeGeneratorMod.addNetworkMessage(CoosingRGUIOfBlockGUIButtonMessage.TYPE, CoosingRGUIOfBlockGUIButtonMessage.STREAM_CODEC, CoosingRGUIOfBlockGUIButtonMessage::handleData);
	}
}