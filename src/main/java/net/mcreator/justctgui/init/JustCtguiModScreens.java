
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.justctgui.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.justctgui.client.gui.FurnaceRemovingRecipesGUIScreen;
import net.mcreator.justctgui.client.gui.FurnaceCTGUIScreen;
import net.mcreator.justctgui.client.gui.CraftingtableCTGUIScreen;
import net.mcreator.justctgui.client.gui.CraftingTableRemovingRecipesGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class JustCtguiModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(JustCtguiModMenus.CRAFTINGTABLE_CTGUI.get(), CraftingtableCTGUIScreen::new);
		event.register(JustCtguiModMenus.FURNACE_CTGUI.get(), FurnaceCTGUIScreen::new);
		event.register(JustCtguiModMenus.CRAFTING_TABLE_REMOVING_RECIPES_GUI.get(), CraftingTableRemovingRecipesGUIScreen::new);
		event.register(JustCtguiModMenus.FURNACE_REMOVING_RECIPES_GUI.get(), FurnaceRemovingRecipesGUIScreen::new);
	}
}
