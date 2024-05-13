
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.justctgui.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.justctgui.client.gui.CraftingtableCTGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class JustCtguiModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(JustCtguiModMenus.CRAFTINGTABLE_CTGUI.get(), CraftingtableCTGUIScreen::new);
	}
}
