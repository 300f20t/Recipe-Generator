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

import net.mcreator.recipe_generator.procedures.ItemInSlot0Procedure;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record CraftingTableRemovingCTGUISlotMessage(int slotID, int x, int y, int z, int changeType, int meta) implements CustomPacketPayload {

	public static final Type<CraftingTableRemovingCTGUISlotMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(RecipeGeneratorMod.MODID, "crafting_table_removing_ctgui_slots"));
	public static final StreamCodec<RegistryFriendlyByteBuf, CraftingTableRemovingCTGUISlotMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, CraftingTableRemovingCTGUISlotMessage message) -> {
		buffer.writeInt(message.slotID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		buffer.writeInt(message.changeType);
		buffer.writeInt(message.meta);
	}, (RegistryFriendlyByteBuf buffer) -> new CraftingTableRemovingCTGUISlotMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<CraftingTableRemovingCTGUISlotMessage> type() {
		return TYPE;
	}

	public static void handleData(final CraftingTableRemovingCTGUISlotMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> handleSlotAction(context.player(), message.slotID, message.changeType, message.meta, message.x, message.y, message.z)).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleSlotAction(Player entity, int slot, int changeType, int meta, int x, int y, int z) {
		Level world = entity.level();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (slot == 0 && changeType == 0) {

			ItemInSlot0Procedure.execute(world, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		RecipeGeneratorMod.addNetworkMessage(CraftingTableRemovingCTGUISlotMessage.TYPE, CraftingTableRemovingCTGUISlotMessage.STREAM_CODEC, CraftingTableRemovingCTGUISlotMessage::handleData);
	}
}