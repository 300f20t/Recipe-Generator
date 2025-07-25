
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

import net.mcreator.recipe_generator.world.inventory.CampFireRGUIMenu;
import net.mcreator.recipe_generator.procedures.ScriptswriterProcedure;
import net.mcreator.recipe_generator.procedures.ReloadCommandProcedure;
import net.mcreator.recipe_generator.procedures.GenerateFurnaceReciepsProcedure;
import net.mcreator.recipe_generator.procedures.GUIcloseProcedure;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import java.util.HashMap;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record CampFireRGUIButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<CampFireRGUIButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(RecipeGeneratorMod.MODID, "camp_fire_rgui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, CampFireRGUIButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, CampFireRGUIButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new CampFireRGUIButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<CampFireRGUIButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final CampFireRGUIButtonMessage message, final IPayloadContext context) {
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
		HashMap guistate = CampFireRGUIMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			GenerateFurnaceReciepsProcedure.execute(world, entity, guistate);
		}
		if (buttonID == 1) {

			ScriptswriterProcedure.execute(world, guistate);
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
		RecipeGeneratorMod.addNetworkMessage(CampFireRGUIButtonMessage.TYPE, CampFireRGUIButtonMessage.STREAM_CODEC, CampFireRGUIButtonMessage::handleData);
	}
}
