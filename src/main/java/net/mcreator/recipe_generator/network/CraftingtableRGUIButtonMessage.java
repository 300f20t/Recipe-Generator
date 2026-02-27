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

import net.mcreator.recipe_generator.procedures.*;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

@EventBusSubscriber
public record CraftingtableRGUIButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<CraftingtableRGUIButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(RecipeGeneratorMod.MODID, "craftingtable_rgui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, CraftingtableRGUIButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, CraftingtableRGUIButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new CraftingtableRGUIButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<CraftingtableRGUIButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final CraftingtableRGUIButtonMessage message, final IPayloadContext context) {
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

			AllmirroraxisProcedure.execute();
		}
		if (buttonID == 1) {

			DiagonalmirroraxisProcedure.execute();
		}
		if (buttonID == 2) {

			HorizontalmirroraxisProcedure.execute();
		}
		if (buttonID == 3) {

			NonemirroraxisProcedure.execute();
		}
		if (buttonID == 4) {

			VerticalmirroraxisProcedure.execute();
		}
		if (buttonID == 5) {

			GenerateCraftingTableRecipeProcedure.execute(world, entity);
		}
		if (buttonID == 6) {

			ScriptswriterProcedure.execute(world, entity);
		}
		if (buttonID == 7) {

			GUIcloseProcedure.execute(entity);
		}
		if (buttonID == 8) {

			ReloadCommandProcedure.execute(world, x, y, z);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		RecipeGeneratorMod.addNetworkMessage(CraftingtableRGUIButtonMessage.TYPE, CraftingtableRGUIButtonMessage.STREAM_CODEC, CraftingtableRGUIButtonMessage::handleData);
	}
}