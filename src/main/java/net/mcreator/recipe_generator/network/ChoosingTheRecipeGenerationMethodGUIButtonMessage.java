
package net.mcreator.recipe_generator.network;

import net.minecraft.world.level.Level;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.recipe_generator.world.inventory.ChoosingTheRecipeGenerationMethodGUIMenu;
import net.mcreator.recipe_generator.procedures.CloseWithSaveOpenedGUIProcedure;
import net.mcreator.recipe_generator.procedures.ChangeSelectedGeneratingMethodToKubeJSProcedure;
import net.mcreator.recipe_generator.procedures.ChangeSelectedGeneratingMethodToCraftTweakerProcedure;

import net.fabricmc.fabric.api.networking.v1.PacketSender;

import java.util.HashMap;

import io.netty.buffer.Unpooled;

public class ChoosingTheRecipeGenerationMethodGUIButtonMessage extends FriendlyByteBuf {
	public ChoosingTheRecipeGenerationMethodGUIButtonMessage(int buttonID, int x, int y, int z) {
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
			HashMap guistate = ChoosingTheRecipeGenerationMethodGUIMenu.guistate;
			if (buttonID == 0) {

				ChangeSelectedGeneratingMethodToCraftTweakerProcedure.execute(world);
			}
			if (buttonID == 1) {

				ChangeSelectedGeneratingMethodToKubeJSProcedure.execute(world);
			}
			if (buttonID == 4) {

				CloseWithSaveOpenedGUIProcedure.execute(world, x, y, z, entity);
			}
		});
	}
}
