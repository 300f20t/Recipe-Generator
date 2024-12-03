
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

import net.mcreator.recipe_generator.world.inventory.ChoosingTheRecipeGenerationMethodGUIMenu;
import net.mcreator.recipe_generator.procedures.CloseWithSaveOpenedGUIProcedure;
import net.mcreator.recipe_generator.procedures.ChangeSelectedGeneratingMethodToKubeJSProcedure;
import net.mcreator.recipe_generator.procedures.ChangeSelectedGeneratingMethodToCraftTweakerProcedure;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import java.util.HashMap;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record ChoosingTheRecipeGenerationMethodGUIButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<ChoosingTheRecipeGenerationMethodGUIButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(RecipeGeneratorMod.MODID, "choosing_the_recipe_generation_method_gui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ChoosingTheRecipeGenerationMethodGUIButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, ChoosingTheRecipeGenerationMethodGUIButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new ChoosingTheRecipeGenerationMethodGUIButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<ChoosingTheRecipeGenerationMethodGUIButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final ChoosingTheRecipeGenerationMethodGUIButtonMessage message, final IPayloadContext context) {
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
		HashMap guistate = ChoosingTheRecipeGenerationMethodGUIMenu.guistate;
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

			CloseWithSaveOpenedGUIProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		RecipeGeneratorMod.addNetworkMessage(ChoosingTheRecipeGenerationMethodGUIButtonMessage.TYPE, ChoosingTheRecipeGenerationMethodGUIButtonMessage.STREAM_CODEC, ChoosingTheRecipeGenerationMethodGUIButtonMessage::handleData);
	}
}
