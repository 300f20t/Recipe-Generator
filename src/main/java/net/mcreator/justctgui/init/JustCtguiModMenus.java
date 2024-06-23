
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.justctgui.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;

import net.mcreator.justctgui.world.inventory.FurnaceRemovingRecipesGUIMenu;
import net.mcreator.justctgui.world.inventory.FurnaceCTGUIMenu;
import net.mcreator.justctgui.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.justctgui.world.inventory.CraftingTableRemovingRecipesGUIMenu;
import net.mcreator.justctgui.JustCtguiMod;

public class JustCtguiModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, JustCtguiMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<CraftingtableCTGUIMenu>> CRAFTINGTABLE_CTGUI = REGISTRY.register("craftingtable_ctgui", () -> IMenuTypeExtension.create(CraftingtableCTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<FurnaceCTGUIMenu>> FURNACE_CTGUI = REGISTRY.register("furnace_ctgui", () -> IMenuTypeExtension.create(FurnaceCTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CraftingTableRemovingRecipesGUIMenu>> CRAFTING_TABLE_REMOVING_RECIPES_GUI = REGISTRY.register("crafting_table_removing_recipes_gui",
			() -> IMenuTypeExtension.create(CraftingTableRemovingRecipesGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<FurnaceRemovingRecipesGUIMenu>> FURNACE_REMOVING_RECIPES_GUI = REGISTRY.register("furnace_removing_recipes_gui", () -> IMenuTypeExtension.create(FurnaceRemovingRecipesGUIMenu::new));
}
