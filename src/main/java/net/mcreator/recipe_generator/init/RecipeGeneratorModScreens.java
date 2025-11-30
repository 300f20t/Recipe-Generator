/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.recipe_generator.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.recipe_generator.client.gui.*;

@EventBusSubscriber(Dist.CLIENT)
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
		event.register(RecipeGeneratorModMenus.AVARITA_CRAFTING_TABLE_RGUI.get(), AvaritaCraftingTableRGUIScreen::new);
		event.register(RecipeGeneratorModMenus.COOSING_RGUI_OF_BLOCK_GUI.get(), CoosingRGUIOfBlockGUIScreen::new);
		event.register(RecipeGeneratorModMenus.CRAFTING_2X_2_RGUI.get(), Crafting2x2RGUIScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}