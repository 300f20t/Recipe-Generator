/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.justctgui.init;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.common.extensions.IForgeContainerType;

import net.minecraft.inventory.container.ContainerType;

import net.mcreator.justctgui.world.inventory.FurnaceRemovingCTGUIMenu;
import net.mcreator.justctgui.world.inventory.FurnaceCTGUIMenu;
import net.mcreator.justctgui.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.justctgui.world.inventory.CraftingTableRemovingCTGUIMenu;
import net.mcreator.justctgui.JustCtguiMod;

public class JustCtguiModMenus {
	public static final DeferredRegister<ContainerType<?>> REGISTRY = new DeferredRegister<>(ForgeRegistries.CONTAINERS, JustCtguiMod.MODID);
	public static final RegistryObject<ContainerType<CraftingtableCTGUIMenu>> CRAFTINGTABLE_CTGUI = REGISTRY.register("craftingtable_ctgui", () -> IForgeContainerType.create(CraftingtableCTGUIMenu::new));
	public static final RegistryObject<ContainerType<FurnaceCTGUIMenu>> FURNACE_CTGUI = REGISTRY.register("furnace_ctgui", () -> IForgeContainerType.create(FurnaceCTGUIMenu::new));
	public static final RegistryObject<ContainerType<CraftingTableRemovingCTGUIMenu>> CRAFTING_TABLE_REMOVING_CTGUI = REGISTRY.register("crafting_table_removing_ctgui", () -> IForgeContainerType.create(CraftingTableRemovingCTGUIMenu::new));
	public static final RegistryObject<ContainerType<FurnaceRemovingCTGUIMenu>> FURNACE_REMOVING_CTGUI = REGISTRY.register("furnace_removing_ctgui", () -> IForgeContainerType.create(FurnaceRemovingCTGUIMenu::new));
}
