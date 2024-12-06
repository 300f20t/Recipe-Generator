
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.justctgui.init;

import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.AbstractContainerMenu;

import net.mcreator.justctgui.world.inventory.FurnaceGUIMenu;
import net.mcreator.justctgui.world.inventory.CraftingtableCTGUIMenu;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class JustCtguiModMenus {
	private static final List<MenuType<?>> REGISTRY = new ArrayList<>();
	public static final MenuType<FurnaceGUIMenu> FURNACE_GUI = register("furnace_gui", (id, inv, extraData) -> new FurnaceGUIMenu(id, inv, extraData));
	public static final MenuType<CraftingtableCTGUIMenu> CRAFTINGTABLE_CTGUI = register("craftingtable_ctgui", (id, inv, extraData) -> new CraftingtableCTGUIMenu(id, inv, extraData));

	private static <T extends AbstractContainerMenu> MenuType<T> register(String registryname, IContainerFactory<T> containerFactory) {
		MenuType<T> menuType = new MenuType<T>(containerFactory);
		menuType.setRegistryName(registryname);
		REGISTRY.add(menuType);
		return menuType;
	}

	@SubscribeEvent
	public static void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new MenuType[0]));
	}
}
