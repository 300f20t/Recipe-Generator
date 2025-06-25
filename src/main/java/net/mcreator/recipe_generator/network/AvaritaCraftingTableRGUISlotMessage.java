
package net.mcreator.recipe_generator.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.recipe_generator.world.inventory.AvaritaCraftingTableRGUIMenu;
import net.mcreator.recipe_generator.procedures.ItemInSlot9Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot8Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot81Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot80Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot7Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot79Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot78Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot77Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot76Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot75Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot74Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot73Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot72Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot71Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot70Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot6Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot69Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot68Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot67Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot66Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot65Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot64Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot63Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot62Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot61Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot60Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot5Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot59Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot58Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot57Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot56Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot55Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot54Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot53Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot52Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot51Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot50Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot4Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot49Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot48Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot47Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot46Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot45Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot44Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot43Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot42Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot41Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot40Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot3Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot39Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot38Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot37Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot36Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot35Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot34Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot33Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot32Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot31Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot30Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot2Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot29Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot28Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot27Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot26Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot25Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot24Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot23Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot22Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot21Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot20Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot1Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot19Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot18Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot17Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot16Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot15Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot14Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot13Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot12Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot11Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot10Procedure;
import net.mcreator.recipe_generator.procedures.ItemInSlot0Procedure;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AvaritaCraftingTableRGUISlotMessage {
	private final int slotID, x, y, z, changeType, meta;

	public AvaritaCraftingTableRGUISlotMessage(int slotID, int x, int y, int z, int changeType, int meta) {
		this.slotID = slotID;
		this.x = x;
		this.y = y;
		this.z = z;
		this.changeType = changeType;
		this.meta = meta;
	}

	public AvaritaCraftingTableRGUISlotMessage(FriendlyByteBuf buffer) {
		this.slotID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
		this.changeType = buffer.readInt();
		this.meta = buffer.readInt();
	}

	public static void buffer(AvaritaCraftingTableRGUISlotMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.slotID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		buffer.writeInt(message.changeType);
		buffer.writeInt(message.meta);
	}

	public static void handler(AvaritaCraftingTableRGUISlotMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int slotID = message.slotID;
			int changeType = message.changeType;
			int meta = message.meta;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleSlotAction(entity, slotID, changeType, meta, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleSlotAction(Player entity, int slot, int changeType, int meta, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = AvaritaCraftingTableRGUIMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
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
		if (slot == 10 && changeType == 0) {

			ItemInSlot10Procedure.execute(world, entity);
		}
		if (slot == 11 && changeType == 0) {

			ItemInSlot11Procedure.execute(world, entity);
		}
		if (slot == 12 && changeType == 0) {

			ItemInSlot12Procedure.execute(world, entity);
		}
		if (slot == 13 && changeType == 0) {

			ItemInSlot13Procedure.execute(world, entity);
		}
		if (slot == 14 && changeType == 0) {

			ItemInSlot14Procedure.execute(world, entity);
		}
		if (slot == 15 && changeType == 0) {

			ItemInSlot15Procedure.execute(world, entity);
		}
		if (slot == 16 && changeType == 0) {

			ItemInSlot16Procedure.execute(world, entity);
		}
		if (slot == 17 && changeType == 0) {

			ItemInSlot17Procedure.execute(world, entity);
		}
		if (slot == 18 && changeType == 0) {

			ItemInSlot18Procedure.execute(world, entity);
		}
		if (slot == 19 && changeType == 0) {

			ItemInSlot19Procedure.execute(world, entity);
		}
		if (slot == 20 && changeType == 0) {

			ItemInSlot20Procedure.execute(world, entity);
		}
		if (slot == 21 && changeType == 0) {

			ItemInSlot21Procedure.execute(world, entity);
		}
		if (slot == 22 && changeType == 0) {

			ItemInSlot22Procedure.execute(world, entity);
		}
		if (slot == 23 && changeType == 0) {

			ItemInSlot23Procedure.execute(world, entity);
		}
		if (slot == 24 && changeType == 0) {

			ItemInSlot24Procedure.execute(world, entity);
		}
		if (slot == 25 && changeType == 0) {

			ItemInSlot25Procedure.execute(world, entity);
		}
		if (slot == 26 && changeType == 0) {

			ItemInSlot26Procedure.execute(world, entity);
		}
		if (slot == 27 && changeType == 0) {

			ItemInSlot27Procedure.execute(world, entity);
		}
		if (slot == 28 && changeType == 0) {

			ItemInSlot28Procedure.execute(world, entity);
		}
		if (slot == 29 && changeType == 0) {

			ItemInSlot29Procedure.execute(world, entity);
		}
		if (slot == 30 && changeType == 0) {

			ItemInSlot30Procedure.execute(world, entity);
		}
		if (slot == 31 && changeType == 0) {

			ItemInSlot31Procedure.execute(world, entity);
		}
		if (slot == 32 && changeType == 0) {

			ItemInSlot32Procedure.execute(world, entity);
		}
		if (slot == 33 && changeType == 0) {

			ItemInSlot33Procedure.execute(world, entity);
		}
		if (slot == 34 && changeType == 0) {

			ItemInSlot34Procedure.execute(world, entity);
		}
		if (slot == 35 && changeType == 0) {

			ItemInSlot35Procedure.execute(world, entity);
		}
		if (slot == 36 && changeType == 0) {

			ItemInSlot36Procedure.execute(world, entity);
		}
		if (slot == 37 && changeType == 0) {

			ItemInSlot37Procedure.execute(world, entity);
		}
		if (slot == 38 && changeType == 0) {

			ItemInSlot38Procedure.execute(world, entity);
		}
		if (slot == 39 && changeType == 0) {

			ItemInSlot39Procedure.execute(world, entity);
		}
		if (slot == 40 && changeType == 0) {

			ItemInSlot40Procedure.execute(world, entity);
		}
		if (slot == 41 && changeType == 0) {

			ItemInSlot41Procedure.execute(world, entity);
		}
		if (slot == 42 && changeType == 0) {

			ItemInSlot42Procedure.execute(world, entity);
		}
		if (slot == 43 && changeType == 0) {

			ItemInSlot43Procedure.execute(world, entity);
		}
		if (slot == 44 && changeType == 0) {

			ItemInSlot44Procedure.execute(world, entity);
		}
		if (slot == 45 && changeType == 0) {

			ItemInSlot45Procedure.execute(world, entity);
		}
		if (slot == 46 && changeType == 0) {

			ItemInSlot46Procedure.execute(world, entity);
		}
		if (slot == 47 && changeType == 0) {

			ItemInSlot47Procedure.execute(world, entity);
		}
		if (slot == 48 && changeType == 0) {

			ItemInSlot48Procedure.execute(world, entity);
		}
		if (slot == 49 && changeType == 0) {

			ItemInSlot49Procedure.execute(world, entity);
		}
		if (slot == 50 && changeType == 0) {

			ItemInSlot50Procedure.execute(world, entity);
		}
		if (slot == 51 && changeType == 0) {

			ItemInSlot51Procedure.execute(world, entity);
		}
		if (slot == 52 && changeType == 0) {

			ItemInSlot52Procedure.execute(world, entity);
		}
		if (slot == 53 && changeType == 0) {

			ItemInSlot53Procedure.execute(world, entity);
		}
		if (slot == 54 && changeType == 0) {

			ItemInSlot54Procedure.execute(world, entity);
		}
		if (slot == 55 && changeType == 0) {

			ItemInSlot55Procedure.execute(world, entity);
		}
		if (slot == 56 && changeType == 0) {

			ItemInSlot56Procedure.execute(world, entity);
		}
		if (slot == 57 && changeType == 0) {

			ItemInSlot57Procedure.execute(world, entity);
		}
		if (slot == 58 && changeType == 0) {

			ItemInSlot58Procedure.execute(world, entity);
		}
		if (slot == 59 && changeType == 0) {

			ItemInSlot59Procedure.execute(world, entity);
		}
		if (slot == 60 && changeType == 0) {

			ItemInSlot60Procedure.execute(world, entity);
		}
		if (slot == 61 && changeType == 0) {

			ItemInSlot61Procedure.execute(world, entity);
		}
		if (slot == 62 && changeType == 0) {

			ItemInSlot62Procedure.execute(world, entity);
		}
		if (slot == 63 && changeType == 0) {

			ItemInSlot63Procedure.execute(world, entity);
		}
		if (slot == 64 && changeType == 0) {

			ItemInSlot64Procedure.execute(world, entity);
		}
		if (slot == 65 && changeType == 0) {

			ItemInSlot65Procedure.execute(world, entity);
		}
		if (slot == 66 && changeType == 0) {

			ItemInSlot66Procedure.execute(world, entity);
		}
		if (slot == 67 && changeType == 0) {

			ItemInSlot67Procedure.execute(world, entity);
		}
		if (slot == 68 && changeType == 0) {

			ItemInSlot68Procedure.execute(world, entity);
		}
		if (slot == 69 && changeType == 0) {

			ItemInSlot69Procedure.execute(world, entity);
		}
		if (slot == 70 && changeType == 0) {

			ItemInSlot70Procedure.execute(world, entity);
		}
		if (slot == 71 && changeType == 0) {

			ItemInSlot71Procedure.execute(world, entity);
		}
		if (slot == 72 && changeType == 0) {

			ItemInSlot72Procedure.execute(world, entity);
		}
		if (slot == 73 && changeType == 0) {

			ItemInSlot73Procedure.execute(world, entity);
		}
		if (slot == 74 && changeType == 0) {

			ItemInSlot74Procedure.execute(world, entity);
		}
		if (slot == 75 && changeType == 0) {

			ItemInSlot75Procedure.execute(world, entity);
		}
		if (slot == 76 && changeType == 0) {

			ItemInSlot76Procedure.execute(world, entity);
		}
		if (slot == 77 && changeType == 0) {

			ItemInSlot77Procedure.execute(world, entity);
		}
		if (slot == 78 && changeType == 0) {

			ItemInSlot78Procedure.execute(world, entity);
		}
		if (slot == 79 && changeType == 0) {

			ItemInSlot79Procedure.execute(world, entity);
		}
		if (slot == 80 && changeType == 0) {

			ItemInSlot80Procedure.execute(world, entity);
		}
		if (slot == 81 && changeType == 0) {

			ItemInSlot81Procedure.execute(world, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		RecipeGeneratorMod.addNetworkMessage(AvaritaCraftingTableRGUISlotMessage.class, AvaritaCraftingTableRGUISlotMessage::buffer, AvaritaCraftingTableRGUISlotMessage::new, AvaritaCraftingTableRGUISlotMessage::handler);
	}
}
