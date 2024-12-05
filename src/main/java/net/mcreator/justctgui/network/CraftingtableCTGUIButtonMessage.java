
package net.mcreator.justctgui.network;

import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.justctgui.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.justctgui.procedures.VerticalmirroraxisProcedure;
import net.mcreator.justctgui.procedures.ScriptswriterProcedure;
import net.mcreator.justctgui.procedures.ReloadCommandProcedure;
import net.mcreator.justctgui.procedures.NonemirroraxisProcedure;
import net.mcreator.justctgui.procedures.HorizontalmirroraxisProcedure;
import net.mcreator.justctgui.procedures.GenerateracipesProcedure;
import net.mcreator.justctgui.procedures.GUIcloseProcedure;
import net.mcreator.justctgui.procedures.DiagonalmirroraxisProcedure;
import net.mcreator.justctgui.procedures.AllmirroraxisProcedure;
import net.mcreator.justctgui.JustCtguiMod;

import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record CraftingtableCTGUIButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final ResourceLocation ID = new ResourceLocation(JustCtguiMod.MODID, "craftingtable_ctgui_buttons");
	public CraftingtableCTGUIButtonMessage(FriendlyByteBuf buffer) {
		this(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt());
	}

	@Override
	public void write(final FriendlyByteBuf buffer) {
		buffer.writeInt(buttonID);
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
	}

	@Override
	public ResourceLocation id() {
		return ID;
	}

	public static void handleData(final CraftingtableCTGUIButtonMessage message, final PlayPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.workHandler().submitAsync(() -> {
				Player entity = context.player().get();
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleButtonAction(entity, buttonID, x, y, z);
			}).exceptionally(e -> {
				context.packetHandler().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
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

			ScriptswriterProcedure.execute(entity, guistate);
		}
		if (buttonID == 7) {

			GUIcloseProcedure.execute(entity);
		}
		if (buttonID == 8) {

			ReloadCommandProcedure.execute(world, x, y, z);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		JustCtguiMod.addNetworkMessage(CraftingtableCTGUIButtonMessage.ID, CraftingtableCTGUIButtonMessage::new, CraftingtableCTGUIButtonMessage::handleData);
	}
}
