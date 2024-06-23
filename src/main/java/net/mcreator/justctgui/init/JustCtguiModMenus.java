
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.justctgui.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.justctgui.world.inventory.FurnaceRemovingRecipesGUIMenu;
import net.mcreator.justctgui.world.inventory.FurnaceCTGUIMenu;
import net.mcreator.justctgui.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.justctgui.world.inventory.CraftingTableRemovingRecipesGUIMenu;
import net.mcreator.justctgui.JustCtguiMod;

public class JustCtguiModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, JustCtguiMod.MODID);
	public static final RegistryObject<MenuType<CraftingtableCTGUIMenu>> CRAFTINGTABLE_CTGUI = REGISTRY.register("craftingtable_ctgui", () -> IForgeMenuType.create(CraftingtableCTGUIMenu::new));
	public static final RegistryObject<MenuType<FurnaceCTGUIMenu>> FURNACE_CTGUI = REGISTRY.register("furnace_ctgui", () -> IForgeMenuType.create(FurnaceCTGUIMenu::new));
	public static final RegistryObject<MenuType<CraftingTableRemovingRecipesGUIMenu>> CRAFTING_TABLE_REMOVING_RECIPES_GUI = REGISTRY.register("crafting_table_removing_recipes_gui",
			() -> IForgeMenuType.create(CraftingTableRemovingRecipesGUIMenu::new));
	public static final RegistryObject<MenuType<FurnaceRemovingRecipesGUIMenu>> FURNACE_REMOVING_RECIPES_GUI = REGISTRY.register("furnace_removing_recipes_gui", () -> IForgeMenuType.create(FurnaceRemovingRecipesGUIMenu::new));
}
