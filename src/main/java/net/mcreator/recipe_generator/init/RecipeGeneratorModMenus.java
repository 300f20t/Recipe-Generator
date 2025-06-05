
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.recipe_generator.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.recipe_generator.world.inventory.SmithingRGUIMenu;
import net.mcreator.recipe_generator.world.inventory.FurnaceRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.FurnaceCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.CraftingTableRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.ChoosingTheRecipeGenerationMethodGUIMenu;
import net.mcreator.recipe_generator.world.inventory.ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu;
import net.mcreator.recipe_generator.world.inventory.CampFireCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.BlastFurnaceRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.BlastFurnaceCTGUIMenu;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

public class RecipeGeneratorModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, RecipeGeneratorMod.MODID);
	public static final RegistryObject<MenuType<CraftingtableCTGUIMenu>> CRAFTINGTABLE_CTGUI = REGISTRY.register("craftingtable_ctgui", () -> IForgeMenuType.create(CraftingtableCTGUIMenu::new));
	public static final RegistryObject<MenuType<FurnaceCTGUIMenu>> FURNACE_CTGUI = REGISTRY.register("furnace_ctgui", () -> IForgeMenuType.create(FurnaceCTGUIMenu::new));
	public static final RegistryObject<MenuType<CraftingTableRemovingCTGUIMenu>> CRAFTING_TABLE_REMOVING_CTGUI = REGISTRY.register("crafting_table_removing_ctgui", () -> IForgeMenuType.create(CraftingTableRemovingCTGUIMenu::new));
	public static final RegistryObject<MenuType<FurnaceRemovingCTGUIMenu>> FURNACE_REMOVING_CTGUI = REGISTRY.register("furnace_removing_ctgui", () -> IForgeMenuType.create(FurnaceRemovingCTGUIMenu::new));
	public static final RegistryObject<MenuType<BlastFurnaceCTGUIMenu>> BLAST_FURNACE_CTGUI = REGISTRY.register("blast_furnace_ctgui", () -> IForgeMenuType.create(BlastFurnaceCTGUIMenu::new));
	public static final RegistryObject<MenuType<BlastFurnaceRemovingCTGUIMenu>> BLAST_FURNACE_REMOVING_CTGUI = REGISTRY.register("blast_furnace_removing_ctgui", () -> IForgeMenuType.create(BlastFurnaceRemovingCTGUIMenu::new));
	public static final RegistryObject<MenuType<ChoosingTheRecipeGenerationMethodGUIMenu>> CHOOSING_THE_RECIPE_GENERATION_METHOD_GUI = REGISTRY.register("choosing_the_recipe_generation_method_gui",
			() -> IForgeMenuType.create(ChoosingTheRecipeGenerationMethodGUIMenu::new));
	public static final RegistryObject<MenuType<ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu>> CHOOSING_THE_RECIPE_GENERATING_METHOD_GUI_WITH_COMMAND = REGISTRY.register("choosing_the_recipe_generating_method_gui_with_command",
			() -> IForgeMenuType.create(ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu::new));
	public static final RegistryObject<MenuType<CampFireCTGUIMenu>> CAMP_FIRE_CTGUI = REGISTRY.register("camp_fire_ctgui", () -> IForgeMenuType.create(CampFireCTGUIMenu::new));
	public static final RegistryObject<MenuType<SmithingRGUIMenu>> SMITHING_RGUI = REGISTRY.register("smithing_rgui", () -> IForgeMenuType.create(SmithingRGUIMenu::new));
}
