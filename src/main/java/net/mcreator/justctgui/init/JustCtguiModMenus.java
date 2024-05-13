
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.justctgui.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;

import net.mcreator.justctgui.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.justctgui.JustCtguiMod;

public class JustCtguiModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, JustCtguiMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<CraftingtableCTGUIMenu>> CRAFTINGTABLE_CTGUI = REGISTRY.register("craftingtable_ctgui", () -> IMenuTypeExtension.create(CraftingtableCTGUIMenu::new));
}
