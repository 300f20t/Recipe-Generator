
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.recipe_generator.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.recipe_generator.client.gui.GenerationMethodSelectionGUIScreen;
import net.mcreator.recipe_generator.client.gui.FurnaceRemovingCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.FurnaceCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.CraftingtableCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.CraftingTableRemovingCTGUIScreen;
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
		event.register(RecipeGeneratorModMenus.GENERATION_METHOD_SELECTION_GUI.get(), GenerationMethodSelectionGUIScreen::new);
	}
}
