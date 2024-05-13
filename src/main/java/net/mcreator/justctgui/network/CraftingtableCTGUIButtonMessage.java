
package net.mcreator.justctgui.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.justctgui.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.justctgui.procedures.VerticalmirroraxisProcedure;
import net.mcreator.justctgui.procedures.ScriptswriterProcedure;
import net.mcreator.justctgui.procedures.NonemirroraxisProcedure;
import net.mcreator.justctgui.procedures.HorizontalmirroraxisProcedure;
import net.mcreator.justctgui.procedures.GenerateracipesProcedure;
import net.mcreator.justctgui.procedures.GUIcloseProcedure;
import net.mcreator.justctgui.procedures.DiagonalmirroraxisProcedure;
import net.mcreator.justctgui.procedures.AllmirroraxisProcedure;
import net.mcreator.justctgui.JustCtguiMod;

import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CraftingtableCTGUIButtonMessage {
	private final int buttonID, x, y, z;

	public CraftingtableCTGUIButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public CraftingtableCTGUIButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(CraftingtableCTGUIButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(CraftingtableCTGUIButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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

			GenerateracipesProcedure.execute(world, guistate);
		}
		if (buttonID == 6) {

			ScriptswriterProcedure.execute(guistate);
		}
		if (buttonID == 7) {

			GUIcloseProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		JustCtguiMod.addNetworkMessage(CraftingtableCTGUIButtonMessage.class, CraftingtableCTGUIButtonMessage::buffer, CraftingtableCTGUIButtonMessage::new, CraftingtableCTGUIButtonMessage::handler);
	}
}
