
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
import net.mcreator.recipe_generator.procedures.Iteminslot9incraftingtableCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.Iteminslot8incraftingtableCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.Iteminslot7incraftingtableCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.Iteminslot6incraftingtableCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.Iteminslot5incraftingtableCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.Iteminslot4incraftingtableCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.Iteminslot3incraftingtableCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.Iteminslot2incraftingtableCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.Iteminslot1incraftingtableCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.Iteminslot0incraftingtableCTGUIProcedure;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import java.util.HashMap;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record CraftingtableCTGUISlotMessage(int slotID, int x, int y, int z, int changeType, int meta) implements CustomPacketPayload {

	public static final Type<CraftingtableCTGUISlotMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(RecipeGeneratorMod.MODID, "craftingtable_ctgui_slots"));
	public static final StreamCodec<RegistryFriendlyByteBuf, CraftingtableCTGUISlotMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, CraftingtableCTGUISlotMessage message) -> {
		buffer.writeInt(message.slotID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		buffer.writeInt(message.changeType);
		buffer.writeInt(message.meta);
	}, (RegistryFriendlyByteBuf buffer) -> new CraftingtableCTGUISlotMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<CraftingtableCTGUISlotMessage> type() {
		return TYPE;
	}

	public static void handleData(final CraftingtableCTGUISlotMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> {
				Player entity = context.player();
				int slotID = message.slotID;
				int changeType = message.changeType;
				int meta = message.meta;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleSlotAction(entity, slotID, changeType, meta, x, y, z);
			}).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleSlotAction(Player entity, int slot, int changeType, int meta, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = CraftingtableCTGUIMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (slot == 0 && changeType == 0) {

			Iteminslot0incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 1 && changeType == 0) {

			Iteminslot1incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 2 && changeType == 0) {

			Iteminslot2incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 3 && changeType == 0) {

			Iteminslot3incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 4 && changeType == 0) {

			Iteminslot4incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 5 && changeType == 0) {

			Iteminslot5incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 6 && changeType == 0) {

			Iteminslot6incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 7 && changeType == 0) {

			Iteminslot7incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 8 && changeType == 0) {

			Iteminslot8incraftingtableCTGUIProcedure.execute(world, entity);
		}
		if (slot == 9 && changeType == 0) {

			Iteminslot9incraftingtableCTGUIProcedure.execute(world, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		RecipeGeneratorMod.addNetworkMessage(CraftingtableCTGUISlotMessage.TYPE, CraftingtableCTGUISlotMessage.STREAM_CODEC, CraftingtableCTGUISlotMessage::handleData);
	}
}
