
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.justctgui.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.justctgui.client.gui.FurnaceRemovingRecipesGUIScreen;
import net.mcreator.justctgui.client.gui.FurnaceCTGUIScreen;
import net.mcreator.justctgui.client.gui.CraftingtableCTGUIScreen;
import net.mcreator.justctgui.client.gui.CraftingTableRemovingRecipesGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class JustCtguiModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(JustCtguiModMenus.CRAFTINGTABLE_CTGUI.get(), CraftingtableCTGUIScreen::new);
			MenuScreens.register(JustCtguiModMenus.FURNACE_CTGUI.get(), FurnaceCTGUIScreen::new);
			MenuScreens.register(JustCtguiModMenus.CRAFTING_TABLE_REMOVING_RECIPES_GUI.get(), CraftingTableRemovingRecipesGUIScreen::new);
			MenuScreens.register(JustCtguiModMenus.FURNACE_REMOVING_RECIPES_GUI.get(), FurnaceRemovingRecipesGUIScreen::new);
		});
	}
}
