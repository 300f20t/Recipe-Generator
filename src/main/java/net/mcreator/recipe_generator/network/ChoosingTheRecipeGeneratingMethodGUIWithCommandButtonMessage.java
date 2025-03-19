
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

import net.mcreator.recipe_generator.world.inventory.ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu;
import net.mcreator.recipe_generator.procedures.GUIcloseProcedure;
import net.mcreator.recipe_generator.procedures.ChangeSelectedGeneratingMethodToKubeJSProcedure;
import net.mcreator.recipe_generator.procedures.ChangeSelectedGeneratingMethodToCraftTweakerProcedure;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import java.util.HashMap;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(RecipeGeneratorMod.MODID, "choosing_the_recipe_generating_method_gui_with_command_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage> STREAM_CODEC = StreamCodec
			.of((RegistryFriendlyByteBuf buffer, ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage message) -> {
				buffer.writeInt(message.buttonID);
				buffer.writeInt(message.x);
				buffer.writeInt(message.y);
				buffer.writeInt(message.z);
			}, (RegistryFriendlyByteBuf buffer) -> new ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage message, final IPayloadContext context) {
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
		HashMap guistate = ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			ChangeSelectedGeneratingMethodToCraftTweakerProcedure.execute(world);
		}
		if (buttonID == 1) {

			ChangeSelectedGeneratingMethodToKubeJSProcedure.execute(world);
		}
		if (buttonID == 4) {

			GUIcloseProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		RecipeGeneratorMod.addNetworkMessage(ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage.TYPE, ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage.STREAM_CODEC,
				ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage::handleData);
	}
}
