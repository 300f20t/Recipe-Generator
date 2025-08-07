package net.mcreator.recipe_generator.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.recipe_generator.procedures.GUIcloseProcedure;
import net.mcreator.recipe_generator.procedures.ChangeSelectedGeneratingMethodToKubeJSProcedure;
import net.mcreator.recipe_generator.procedures.ChangeSelectedGeneratingMethodToCraftTweakerProcedure;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public static void handleData(final ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage message, final IPayloadContext context) {
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
		RecipeGeneratorMod.addNetworkMessage(ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage.class, ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage::buffer, ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage::new,
				ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage::handler);
	}
}