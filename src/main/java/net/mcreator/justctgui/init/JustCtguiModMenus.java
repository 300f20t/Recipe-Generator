
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.justctgui.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;

import net.mcreator.justctgui.world.inventory.FurnaceRemoveRecipeCTGUIMenu;
import net.mcreator.justctgui.world.inventory.FurnaceCTGUIMenu;
import net.mcreator.justctgui.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.justctgui.world.inventory.CraftingTableRemoveRecipeCTGUIMenu;
import net.mcreator.justctgui.JustCtguiMod;

public class JustCtguiModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, JustCtguiMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<CraftingtableCTGUIMenu>> CRAFTINGTABLE_CTGUI = REGISTRY.register("craftingtable_ctgui", () -> IMenuTypeExtension.create(CraftingtableCTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<FurnaceCTGUIMenu>> FURNACE_CTGUI = REGISTRY.register("furnace_ctgui", () -> IMenuTypeExtension.create(FurnaceCTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CraftingTableRemoveRecipeCTGUIMenu>> CRAFTING_TABLE_REMOVE_RECIPE_CTGUI = REGISTRY.register("crafting_table_remove_recipe_ctgui",
			() -> IMenuTypeExtension.create(CraftingTableRemoveRecipeCTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<FurnaceRemoveRecipeCTGUIMenu>> FURNACE_REMOVE_RECIPE_CTGUI = REGISTRY.register("furnace_remove_recipe_ctgui", () -> IMenuTypeExtension.create(FurnaceRemoveRecipeCTGUIMenu::new));
}
