
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.recipe_generator.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.Minecraft;

import net.mcreator.recipe_generator.init.RecipeGeneratorModMenus.GuiSyncMessage;
import net.mcreator.recipe_generator.client.gui.FurnaceRemovingCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.FurnaceCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.CraftingtableCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.CraftingTableRemovingCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.ChoosingTheRecipeGenerationMethodGUIScreen;
import net.mcreator.recipe_generator.client.gui.ChoosingTheRecipeGeneratingMethodGUIWithCommandScreen;
import net.mcreator.recipe_generator.client.gui.BlastFurnaceRemovingCTGUIScreen;
import net.mcreator.recipe_generator.client.gui.BlastFurnaceCTGUIScreen;

import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RecipeGeneratorModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(RecipeGeneratorModMenus.CRAFTINGTABLE_CTGUI.get(), CraftingtableCTGUIScreen::new);
			MenuScreens.register(RecipeGeneratorModMenus.FURNACE_CTGUI.get(), FurnaceCTGUIScreen::new);
			MenuScreens.register(RecipeGeneratorModMenus.CRAFTING_TABLE_REMOVING_CTGUI.get(), CraftingTableRemovingCTGUIScreen::new);
			MenuScreens.register(RecipeGeneratorModMenus.FURNACE_REMOVING_CTGUI.get(), FurnaceRemovingCTGUIScreen::new);
			MenuScreens.register(RecipeGeneratorModMenus.BLAST_FURNACE_CTGUI.get(), BlastFurnaceCTGUIScreen::new);
			MenuScreens.register(RecipeGeneratorModMenus.BLAST_FURNACE_REMOVING_CTGUI.get(), BlastFurnaceRemovingCTGUIScreen::new);
			MenuScreens.register(RecipeGeneratorModMenus.CHOOSING_THE_RECIPE_GENERATION_METHOD_GUI.get(), ChoosingTheRecipeGenerationMethodGUIScreen::new);
			MenuScreens.register(RecipeGeneratorModMenus.CHOOSING_THE_RECIPE_GENERATING_METHOD_GUI_WITH_COMMAND.get(), ChoosingTheRecipeGeneratingMethodGUIWithCommandScreen::new);
		});
	}

	static void handleTextBoxMessage(GuiSyncMessage message) {
		String editbox = message.editbox();
		String value = message.value();
		Screen currentScreen = Minecraft.getInstance().screen;
		if (currentScreen instanceof WidgetScreen sc) {
			HashMap<String, Object> widgets = sc.getWidgets();
			Object obj = widgets.get("text:" + editbox);
			if (obj instanceof EditBox box) {
				box.setValue(value);
			}
		}
	}

	public interface WidgetScreen {
		HashMap<String, Object> getWidgets();
	}
}
