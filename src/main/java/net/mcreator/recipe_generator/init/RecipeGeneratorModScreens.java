
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.recipe_generator.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.recipe_generator.client.gui.SmithingRGUIScreen;
import net.mcreator.recipe_generator.client.gui.FurnaceRemovingCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.FurnaceCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.CraftingtableCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.CraftingTableRemovingCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.ChoosingTheRecipeGenerationMethodGUIScreen;
import net.mcreator.recipe_generator.client.gui.ChoosingTheRecipeGeneratingMethodGUIWithCommandScreen;
import net.mcreator.recipe_generator.client.gui.CampFireCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.BlastFurnaceRemovingCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.BlastFurnaceCTGUIScreen;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
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
		event.register(RecipeGeneratorModMenus.CAMP_FIRE_CTGUI.get(), CampFireCTGUIScreen::new);
		event.register(RecipeGeneratorModMenus.SMITHING_RGUI.get(), SmithingRGUIScreen::new);
	}
}
