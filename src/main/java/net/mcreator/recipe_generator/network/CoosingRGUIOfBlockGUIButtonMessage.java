package net.mcreator.recipe_generator.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.recipe_generator.procedures.ButtonRGUIOfBlock4Procedure;
import net.mcreator.recipe_generator.procedures.ButtonRGUIOfBlock3Procedure;
import net.mcreator.recipe_generator.procedures.ButtonRGUIOfBlock2Procedure;
import net.mcreator.recipe_generator.procedures.ButtonRGUIOfBlock1Procedure;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CoosingRGUIOfBlockGUIButtonMessage {
	private final int buttonID, x, y, z;

	public CoosingRGUIOfBlockGUIButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public CoosingRGUIOfBlockGUIButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(CoosingRGUIOfBlockGUIButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(CoosingRGUIOfBlockGUIButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			ButtonRGUIOfBlock1Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			ButtonRGUIOfBlock2Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			ButtonRGUIOfBlock3Procedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 3) {

			ButtonRGUIOfBlock4Procedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		RecipeGeneratorMod.addNetworkMessage(CoosingRGUIOfBlockGUIButtonMessage.class, CoosingRGUIOfBlockGUIButtonMessage::buffer, CoosingRGUIOfBlockGUIButtonMessage::new, CoosingRGUIOfBlockGUIButtonMessage::handler);
	}
}