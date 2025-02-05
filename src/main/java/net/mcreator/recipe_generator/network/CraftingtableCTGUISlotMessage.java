
package net.mcreator.recipe_generator.network;

import net.minecraft.world.level.Level;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.recipe_generator.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.recipe_generator.procedures.ItemInSlot9Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot8Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot7Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot6Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot5Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot4Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot3Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot2Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot1Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot0Procedure;

import net.fabricmc.fabric.api.networking.v1.PacketSender;

import java.util.HashMap;

import io.netty.buffer.Unpooled;

public class CraftingtableCTGUISlotMessage extends FriendlyByteBuf {
	public CraftingtableCTGUISlotMessage(int slot, int x, int y, int z, int changeType, int meta) {
		super(Unpooled.buffer());
		writeInt(slot);
		writeInt(x);
		writeInt(y);
		writeInt(z);
		writeInt(changeType);
		writeInt(meta);
	}

	public static void apply(MinecraftServer server, ServerPlayer entity, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
		int slot = buf.readInt();
		double x = buf.readInt();
		double y = buf.readInt();
		double z = buf.readInt();
		int changeType = buf.readInt();
		int meta = buf.readInt();
		server.execute(() -> {
			Level world = entity.level();
			HashMap guistate = CraftingtableCTGUIMenu.guistate;
			if (slot == 0 && changeType == 0) {

				ItemInSlot0Procedure.execute(world, entity);
			}
			if (slot == 1 && changeType == 0) {

				ItemInSlot1Procedure.execute(world, entity);
			}
			if (slot == 2 && changeType == 0) {

				ItemInSlot2Procedure.execute(world, entity);
			}
			if (slot == 3 && changeType == 0) {

				ItemInSlot3Procedure.execute(world, entity);
			}
			if (slot == 4 && changeType == 0) {

				ItemInSlot4Procedure.execute(world, entity);
			}
			if (slot == 5 && changeType == 0) {

				ItemInSlot5Procedure.execute(world, entity);
			}
			if (slot == 6 && changeType == 0) {

				ItemInSlot6Procedure.execute(world, entity);
			}
			if (slot == 7 && changeType == 0) {

				ItemInSlot7Procedure.execute(world, entity);
			}
			if (slot == 8 && changeType == 0) {

				ItemInSlot8Procedure.execute(world, entity);
			}
			if (slot == 9 && changeType == 0) {

				ItemInSlot9Procedure.execute(world, entity);
			}
		});
	}
}
