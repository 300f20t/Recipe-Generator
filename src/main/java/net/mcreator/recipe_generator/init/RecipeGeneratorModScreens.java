/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.recipe_generator.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.recipe_generator.client.gui.SmithingRGUIScreen;
import net.mcreator.recipe_generator.client.gui.FurnaceRemovingCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.FurnaceCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.CraftingtableCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.CraftingTableRemovingCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.ChoosingTheRecipeGenerationMethodGUIScreen;
import net.mcreator.recipe_generator.client.gui.ChoosingTheRecipeGeneratingMethodGUIWithCommandScreen;
import net.mcreator.recipe_generator.client.gui.CampFireRemovingRGUIScreen;
import net.mcreator.recipe_generator.client.gui.CampFireRGUIScreen;
import net.mcreator.recipe_generator.client.gui.BlastFurnaceRemovingCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.BlastFurnaceCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.AvaritaCraftingTableRGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RecipeGeneratorModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(RecipeGeneratorModMenus.CRAFTINGTABLE_CTGUI.get(), CraftingtableCTGUIScreen::new);
		event.register(RecipeGeneratorModMenus.FURNACE_CTGUI.get(), FurnaceCTGUIScreen::new);
		event.register(RecipeGeneratorModMenus.CRAFTING_TABLE_REMOVING_CTGUI.get(), CraftingTableRemovingCTGUIScreen::new);
		event.register(RecipeGeneratorModMenus.FURNACE_REMOVING_CTGUI.get(), FurnaceRemovingCTGUIScreen::new);
		event.register(RecipeGeneratorModMenus.BLAST_FURNACE_CTGUI.get(), BlastFurnaceCTGUIScreen::new);
		event.register(RecipeGeneratorModMenus.BLAST_FURNACE_REMOVING_CTGUI.get(), BlastFurnaceRemovingCTGUIScreen::new);
		event.register(RecipeGeneratorModMenus.CHOOSING_THE_RECIPE_GENERATION_METHOD_GUI.get(), ChoosingTheRecipeGenerationMethodGUIScreen::new);
		event.register(RecipeGeneratorModMenus.CHOOSING_THE_RECIPE_GENERATING_METHOD_GUI_WITH_COMMAND.get(), ChoosingTheRecipeGeneratingMethodGUIWithCommandScreen::new);
		event.register(RecipeGeneratorModMenus.SMITHING_RGUI.get(), SmithingRGUIScreen::new);
		event.register(RecipeGeneratorModMenus.CAMP_FIRE_REMOVING_RGUI.get(), CampFireRemovingRGUIScreen::new);
		event.register(RecipeGeneratorModMenus.CAMP_FIRE_RGUI.get(), CampFireRGUIScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}