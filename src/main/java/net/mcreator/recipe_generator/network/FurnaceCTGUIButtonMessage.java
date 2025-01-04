
package net.mcreator.recipe_generator.network;

import net.minecraft.world.level.Level;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.recipe_generator.world.inventory.FurnaceCTGUIMenu;
import net.mcreator.recipe_generator.procedures.ReloadCommandProcedure;
import net.mcreator.recipe_generator.procedures.GenerateFurnaceReciepsProcedure;
import net.mcreator.recipe_generator.procedures.GUIcloseProcedure;

import net.fabricmc.fabric.api.networking.v1.PacketSender;

import java.util.HashMap;

import io.netty.buffer.Unpooled;

public class FurnaceCTGUIButtonMessage extends FriendlyByteBuf {
	public FurnaceCTGUIButtonMessage(int buttonID, int x, int y, int z) {
		super(Unpooled.buffer());
		writeInt(buttonID);
		writeInt(x);
		writeInt(y);
		writeInt(z);
	}

	public static void apply(MinecraftServer server, ServerPlayer entity, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
		int buttonID = buf.readInt();
		double x = buf.readInt();
		double y = buf.readInt();
		double z = buf.readInt();
		server.execute(() -> {
			Level world = entity.level();
			HashMap guistate = FurnaceCTGUIMenu.guistate;
			if (buttonID == 0) {

				GenerateFurnaceReciepsProcedure.execute(world, guistate);
			}
			if (buttonID == 1) {

				ScriptswriterProcedure.execute(world, guistate);
			}
			if (buttonID == 2) {

				GUIcloseProcedure.execute(entity);
			}
			if (buttonID == 3) {

				ReloadCommandProcedure.execute(world, x, y, z);
			}
		});
	}
}
