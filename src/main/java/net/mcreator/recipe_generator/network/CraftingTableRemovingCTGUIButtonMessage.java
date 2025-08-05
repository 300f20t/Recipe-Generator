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

import net.mcreator.recipe_generator.procedures.ScriptswriterProcedure;
import net.mcreator.recipe_generator.procedures.ReloadCommandProcedure;
import net.mcreator.recipe_generator.procedures.GenerateCraftingTableRemovingRecipeProcedure;
import net.mcreator.recipe_generator.procedures.GUIcloseProcedure;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record CraftingTableRemovingCTGUIButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<CraftingTableRemovingCTGUIButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(RecipeGeneratorMod.MODID, "crafting_table_removing_ctgui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, CraftingTableRemovingCTGUIButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, CraftingTableRemovingCTGUIButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new CraftingTableRemovingCTGUIButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<CraftingTableRemovingCTGUIButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final CraftingTableRemovingCTGUIButtonMessage message, final IPayloadContext context) {
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

			GenerateCraftingTableRemovingRecipeProcedure.execute(world, entity);
		}
		if (buttonID == 1) {

			ScriptswriterProcedure.execute(world, entity);
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
		RecipeGeneratorMod.addNetworkMessage(CraftingTableRemovingCTGUIButtonMessage.TYPE, CraftingTableRemovingCTGUIButtonMessage.STREAM_CODEC, CraftingTableRemovingCTGUIButtonMessage::handleData);
	}
}