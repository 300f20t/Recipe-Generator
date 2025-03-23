
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

import net.mcreator.recipe_generator.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.recipe_generator.procedures.VerticalmirroraxisProcedure;
import net.mcreator.recipe_generator.procedures.ScriptswriterProcedure;
import net.mcreator.recipe_generator.procedures.ReloadCommandProcedure;
import net.mcreator.recipe_generator.procedures.NonemirroraxisProcedure;
import net.mcreator.recipe_generator.procedures.HorizontalmirroraxisProcedure;
import net.mcreator.recipe_generator.procedures.GenerateCraftingTableRecipeProcedure;
import net.mcreator.recipe_generator.procedures.GUIcloseProcedure;
import net.mcreator.recipe_generator.procedures.DiagonalmirroraxisProcedure;
import net.mcreator.recipe_generator.procedures.AllmirroraxisProcedure;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import java.util.HashMap;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record CraftingtableCTGUIButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<CraftingtableCTGUIButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(RecipeGeneratorMod.MODID, "craftingtable_ctgui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, CraftingtableCTGUIButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, CraftingtableCTGUIButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new CraftingtableCTGUIButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<CraftingtableCTGUIButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final CraftingtableCTGUIButtonMessage message, final IPayloadContext context) {
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
		HashMap guistate = CraftingtableCTGUIMenu.guistate;
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

			GenerateCraftingTableRecipeProcedure.execute(world, entity, guistate);
		}
		if (buttonID == 6) {

			ScriptswriterProcedure.execute(world, guistate);
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
		RecipeGeneratorMod.addNetworkMessage(CraftingtableCTGUIButtonMessage.TYPE, CraftingtableCTGUIButtonMessage.STREAM_CODEC, CraftingtableCTGUIButtonMessage::handleData);
	}
}
