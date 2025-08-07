package net.mcreator.recipe_generator.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.recipe_generator.procedures.ScriptswriterProcedure;
import net.mcreator.recipe_generator.procedures.ReloadCommandProcedure;
import net.mcreator.recipe_generator.procedures.GenerateFurnaceReciepsProcedure;
import net.mcreator.recipe_generator.procedures.GUIcloseProcedure;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

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

			GenerateFurnaceReciepsProcedure.execute(world, entity);
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
		RecipeGeneratorMod.addNetworkMessage(CampFireRGUIButtonMessage.TYPE, CampFireRGUIButtonMessage.STREAM_CODEC, CampFireRGUIButtonMessage::handleData);
	}
}