/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.recipe_generator.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.Minecraft;

import net.mcreator.recipe_generator.world.inventory.SmithingRGUIMenu;
import net.mcreator.recipe_generator.world.inventory.FurnaceRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.FurnaceCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.CraftingTableRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.CoosingRGUIOfBlockGUIMenu;
import net.mcreator.recipe_generator.world.inventory.ChoosingTheRecipeGenerationMethodGUIMenu;
import net.mcreator.recipe_generator.world.inventory.ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu;
import net.mcreator.recipe_generator.world.inventory.BlastFurnaceRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.BlastFurnaceCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.AvaritaCraftingTableRGUIMenu;
import net.mcreator.recipe_generator.network.MenuStateUpdateMessage;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import java.util.Map;

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
	public static final RegistryObject<MenuType<SmithingRGUIMenu>> SMITHING_RGUI = REGISTRY.register("smithing_rgui", () -> IForgeMenuType.create(SmithingRGUIMenu::new));
	public static final RegistryObject<MenuType<AvaritaCraftingTableRGUIMenu>> AVARITA_CRAFTING_TABLE_RGUI = REGISTRY.register("avarita_crafting_table_rgui", () -> IForgeMenuType.create(AvaritaCraftingTableRGUIMenu::new));
	public static final RegistryObject<MenuType<CoosingRGUIOfBlockGUIMenu>> COOSING_RGUI_OF_BLOCK_GUI = REGISTRY.register("coosing_rgui_of_block_gui", () -> IForgeMenuType.create(CoosingRGUIOfBlockGUIMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				RecipeGeneratorMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide) {
				if (Minecraft.getInstance().screen instanceof RecipeGeneratorModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				RecipeGeneratorMod.PACKET_HANDLER.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}
}