/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.justctgui.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.ScreenManager;

import net.mcreator.justctgui.client.gui.FurnaceRemovingCTGUIScreen;
import net.mcreator.justctgui.client.gui.FurnaceCTGUIScreen;
import net.mcreator.justctgui.client.gui.CraftingtableCTGUIScreen;
import net.mcreator.justctgui.client.gui.CraftingTableRemovingCTGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class JustCtguiModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		ScreenManager.registerFactory(JustCtguiModMenus.CRAFTINGTABLE_CTGUI.get(), CraftingtableCTGUIScreen::new);
		ScreenManager.registerFactory(JustCtguiModMenus.FURNACE_CTGUI.get(), FurnaceCTGUIScreen::new);
		ScreenManager.registerFactory(JustCtguiModMenus.CRAFTING_TABLE_REMOVING_CTGUI.get(), CraftingTableRemovingCTGUIScreen::new);
		ScreenManager.registerFactory(JustCtguiModMenus.FURNACE_REMOVING_CTGUI.get(), FurnaceRemovingCTGUIScreen::new);
	}
}
