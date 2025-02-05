
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.recipe_generator.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.recipe_generator.world.inventory.FurnaceRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.FurnaceCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.CraftingTableRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.ChoosingTheRecipeGenerationMethodGUIMenu;
import net.mcreator.recipe_generator.world.inventory.ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu;
import net.mcreator.recipe_generator.world.inventory.BlastFurnaceRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.BlastFurnaceCTGUIMenu;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import javax.annotation.Nullable;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RecipeGeneratorModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, RecipeGeneratorMod.MODID);
	public static final RegistryObject<MenuType<CraftingtableCTGUIMenu>> CRAFTINGTABLE_CTGUI = REGISTRY.register("craftingtable_ctgui", () -> IForgeMenuType.create(CraftingtableCTGUIMenu::new));
	public static final RegistryObject<MenuType<FurnaceCTGUIMenu>> FURNACE_CTGUI = REGISTRY.register("furnace_ctgui", () -> IForgeMenuType.create(FurnaceCTGUIMenu::new));
	public static final RegistryObject<MenuType<CraftingTableRemovingCTGUIMenu>> CRAFTING_TABLE_REMOVING_CTGUI = REGISTRY.register("crafting_table_removing_ctgui", () -> IForgeMenuType.create(CraftingTableRemovingCTGUIMenu::new));
	public static final RegistryObject<MenuType<FurnaceRemovingCTGUIMenu>> FURNACE_REMOVING_CTGUI = REGISTRY.register("furnace_removing_ctgui", () -> IForgeMenuType.create(FurnaceRemovingCTGUIMenu::new));
	public static final RegistryObject<MenuType<BlastFurnaceCTGUIMenu>> BLAST_FURNACE_CTGUI = REGISTRY.register("blast_furnace_ctgui", () -> IForgeMenuType.create(BlastFurnaceCTGUIMenu::new));
	public static final RegistryObject<MenuType<BlastFurnaceRemovingCTGUIMenu>> BLAST_FURNACE_REMOVING_CTGUI = REGISTRY.register("blast_furnace_removing_ctgui", () -> IForgeMenuType.create(BlastFurnaceRemovingCTGUIMenu::new));
	public static final RegistryObject<MenuType<ChoosingTheRecipeGenerationMethodGUIMenu>> CHOOSING_THE_RECIPE_GENERATION_METHOD_GUI = REGISTRY.register("choosing_the_recipe_generation_method_gui",
			() -> IForgeMenuType.create(ChoosingTheRecipeGenerationMethodGUIMenu::new));
	public static final RegistryObject<MenuType<ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu>> CHOOSING_THE_RECIPE_GENERATING_METHOD_GUI_WITH_COMMAND = REGISTRY.register("choosing_the_recipe_generating_method_gui_with_command",
			() -> IForgeMenuType.create(ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu::new));

	public static void setText(String boxname, String value, @Nullable ServerPlayer player) {
		if (player != null) {
			RecipeGeneratorMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new GuiSyncMessage(boxname, value));
		} else {
			RecipeGeneratorMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new GuiSyncMessage(boxname, value));
		}
	}

	public static class GuiSyncMessage {
		private final String textboxid;
		private final String data;

		public GuiSyncMessage(FriendlyByteBuf buffer) {
			this.textboxid = buffer.readComponent().getString();
			this.data = buffer.readComponent().getString();
		}

		public GuiSyncMessage(String textboxid, String data) {
			this.textboxid = textboxid;
			this.data = data;
		}

		public static void buffer(GuiSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeComponent(Component.literal(message.textboxid));
			buffer.writeComponent(Component.literal(message.data));
		}

		public static void handleData(GuiSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					RecipeGeneratorModScreens.handleTextBoxMessage(message);
				}
			});
			context.setPacketHandled(true);
		}

		String editbox() {
			return this.textboxid;
		}

		String value() {
			return this.data;
		}
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		RecipeGeneratorMod.addNetworkMessage(GuiSyncMessage.class, GuiSyncMessage::buffer, GuiSyncMessage::new, GuiSyncMessage::handleData);
	}
}
