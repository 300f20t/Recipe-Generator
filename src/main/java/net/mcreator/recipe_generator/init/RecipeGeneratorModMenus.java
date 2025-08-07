/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.recipe_generator.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import net.mcreator.recipe_generator.world.inventory.SmithingRGUIMenu;
import net.mcreator.recipe_generator.world.inventory.FurnaceRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.FurnaceCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.CraftingTableRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.ChoosingTheRecipeGenerationMethodGUIMenu;
import net.mcreator.recipe_generator.world.inventory.ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu;
import net.mcreator.recipe_generator.world.inventory.CampFireRemovingRGUIMenu;
import net.mcreator.recipe_generator.world.inventory.CampFireRGUIMenu;
import net.mcreator.recipe_generator.world.inventory.BlastFurnaceRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.BlastFurnaceCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.AvaritaCraftingTableRGUIMenu;
import net.mcreator.recipe_generator.network.MenuStateUpdateMessage;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import java.util.Map;

public class RecipeGeneratorModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, RecipeGeneratorMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<CraftingtableCTGUIMenu>> CRAFTINGTABLE_CTGUI = REGISTRY.register("craftingtable_ctgui", () -> IMenuTypeExtension.create(CraftingtableCTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<FurnaceCTGUIMenu>> FURNACE_CTGUI = REGISTRY.register("furnace_ctgui", () -> IMenuTypeExtension.create(FurnaceCTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CraftingTableRemovingCTGUIMenu>> CRAFTING_TABLE_REMOVING_CTGUI = REGISTRY.register("crafting_table_removing_ctgui", () -> IMenuTypeExtension.create(CraftingTableRemovingCTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<FurnaceRemovingCTGUIMenu>> FURNACE_REMOVING_CTGUI = REGISTRY.register("furnace_removing_ctgui", () -> IMenuTypeExtension.create(FurnaceRemovingCTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<BlastFurnaceCTGUIMenu>> BLAST_FURNACE_CTGUI = REGISTRY.register("blast_furnace_ctgui", () -> IMenuTypeExtension.create(BlastFurnaceCTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<BlastFurnaceRemovingCTGUIMenu>> BLAST_FURNACE_REMOVING_CTGUI = REGISTRY.register("blast_furnace_removing_ctgui", () -> IMenuTypeExtension.create(BlastFurnaceRemovingCTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ChoosingTheRecipeGenerationMethodGUIMenu>> CHOOSING_THE_RECIPE_GENERATION_METHOD_GUI = REGISTRY.register("choosing_the_recipe_generation_method_gui",
			() -> IMenuTypeExtension.create(ChoosingTheRecipeGenerationMethodGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu>> CHOOSING_THE_RECIPE_GENERATING_METHOD_GUI_WITH_COMMAND = REGISTRY.register("choosing_the_recipe_generating_method_gui_with_command",
			() -> IMenuTypeExtension.create(ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<SmithingRGUIMenu>> SMITHING_RGUI = REGISTRY.register("smithing_rgui", () -> IMenuTypeExtension.create(SmithingRGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CampFireRemovingRGUIMenu>> CAMP_FIRE_REMOVING_RGUI = REGISTRY.register("camp_fire_removing_rgui", () -> IMenuTypeExtension.create(CampFireRemovingRGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CampFireRGUIMenu>> CAMP_FIRE_RGUI = REGISTRY.register("camp_fire_rgui", () -> IMenuTypeExtension.create(CampFireRGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<AvaritaCraftingTableRGUIMenu>> AVARITA_CRAFTING_TABLE_RGUI = REGISTRY.register("avarita_crafting_table_rgui", () -> IMenuTypeExtension.create(AvaritaCraftingTableRGUIMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				PacketDistributor.sendToPlayer(serverPlayer, new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide) {
				if (Minecraft.getInstance().screen instanceof RecipeGeneratorModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				PacketDistributor.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
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