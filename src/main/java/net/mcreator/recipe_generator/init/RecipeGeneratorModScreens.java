
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.recipe_generator.init;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.recipe_generator.client.gui.FurnaceRemovingCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.FurnaceCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.CraftingtableCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.CraftingTableRemovingCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.ChoosingTheRecipeGenerationMethodGUIScreen;
import net.mcreator.recipe_generator.client.gui.ChoosingTheRecipeGeneratingMethodGUIWithCommandScreen;
import net.mcreator.recipe_generator.client.gui.BlastFurnaceRemovingCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.BlastFurnaceCTGUIScreen;

public class RecipeGeneratorModScreens {
	public static void load() {
		MenuScreens.register(RecipeGeneratorModMenus.CRAFTINGTABLE_CTGUI, CraftingtableCTGUIScreen::new);
		MenuScreens.register(RecipeGeneratorModMenus.FURNACE_CTGUI, FurnaceCTGUIScreen::new);
		MenuScreens.register(RecipeGeneratorModMenus.CRAFTING_TABLE_REMOVING_CTGUI, CraftingTableRemovingCTGUIScreen::new);
		MenuScreens.register(RecipeGeneratorModMenus.FURNACE_REMOVING_CTGUI, FurnaceRemovingCTGUIScreen::new);
		MenuScreens.register(RecipeGeneratorModMenus.BLAST_FURNACE_CTGUI, BlastFurnaceCTGUIScreen::new);
		MenuScreens.register(RecipeGeneratorModMenus.BLAST_FURNACE_REMOVING_CTGUI, BlastFurnaceRemovingCTGUIScreen::new);
		MenuScreens.register(RecipeGeneratorModMenus.CHOOSING_THE_RECIPE_GENERATION_METHOD_GUI, ChoosingTheRecipeGenerationMethodGUIScreen::new);
		MenuScreens.register(RecipeGeneratorModMenus.CHOOSING_THE_RECIPE_GENERATING_METHOD_GUI_WITH_COMMAND, ChoosingTheRecipeGeneratingMethodGUIWithCommandScreen::new);
	}
}
