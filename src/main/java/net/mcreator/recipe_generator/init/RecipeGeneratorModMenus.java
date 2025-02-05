
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.recipe_generator.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;

import net.mcreator.recipe_generator.world.inventory.FurnaceRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.FurnaceCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.CraftingTableRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.ChoosingTheRecipeGenerationMethodGUIMenu;
import net.mcreator.recipe_generator.world.inventory.ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu;
import net.mcreator.recipe_generator.world.inventory.BlastFurnaceRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.BlastFurnaceCTGUIMenu;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;

public class RecipeGeneratorModMenus {
	public static MenuType<CraftingtableCTGUIMenu> CRAFTINGTABLE_CTGUI;
	public static MenuType<FurnaceCTGUIMenu> FURNACE_CTGUI;
	public static MenuType<CraftingTableRemovingCTGUIMenu> CRAFTING_TABLE_REMOVING_CTGUI;
	public static MenuType<FurnaceRemovingCTGUIMenu> FURNACE_REMOVING_CTGUI;
	public static MenuType<BlastFurnaceCTGUIMenu> BLAST_FURNACE_CTGUI;
	public static MenuType<BlastFurnaceRemovingCTGUIMenu> BLAST_FURNACE_REMOVING_CTGUI;
	public static MenuType<ChoosingTheRecipeGenerationMethodGUIMenu> CHOOSING_THE_RECIPE_GENERATION_METHOD_GUI;
	public static MenuType<ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu> CHOOSING_THE_RECIPE_GENERATING_METHOD_GUI_WITH_COMMAND;

	public static void load() {
		CRAFTINGTABLE_CTGUI = Registry.register(BuiltInRegistries.MENU, new ResourceLocation(RecipeGeneratorMod.MODID, "craftingtable_ctgui"), new ExtendedScreenHandlerType<>(CraftingtableCTGUIMenu::new));
		CraftingtableCTGUIMenu.screenInit();
		FURNACE_CTGUI = Registry.register(BuiltInRegistries.MENU, new ResourceLocation(RecipeGeneratorMod.MODID, "furnace_ctgui"), new ExtendedScreenHandlerType<>(FurnaceCTGUIMenu::new));
		FurnaceCTGUIMenu.screenInit();
		CRAFTING_TABLE_REMOVING_CTGUI = Registry.register(BuiltInRegistries.MENU, new ResourceLocation(RecipeGeneratorMod.MODID, "crafting_table_removing_ctgui"), new ExtendedScreenHandlerType<>(CraftingTableRemovingCTGUIMenu::new));
		CraftingTableRemovingCTGUIMenu.screenInit();
		FURNACE_REMOVING_CTGUI = Registry.register(BuiltInRegistries.MENU, new ResourceLocation(RecipeGeneratorMod.MODID, "furnace_removing_ctgui"), new ExtendedScreenHandlerType<>(FurnaceRemovingCTGUIMenu::new));
		FurnaceRemovingCTGUIMenu.screenInit();
		BLAST_FURNACE_CTGUI = Registry.register(BuiltInRegistries.MENU, new ResourceLocation(RecipeGeneratorMod.MODID, "blast_furnace_ctgui"), new ExtendedScreenHandlerType<>(BlastFurnaceCTGUIMenu::new));
		BlastFurnaceCTGUIMenu.screenInit();
		BLAST_FURNACE_REMOVING_CTGUI = Registry.register(BuiltInRegistries.MENU, new ResourceLocation(RecipeGeneratorMod.MODID, "blast_furnace_removing_ctgui"), new ExtendedScreenHandlerType<>(BlastFurnaceRemovingCTGUIMenu::new));
		BlastFurnaceRemovingCTGUIMenu.screenInit();
		CHOOSING_THE_RECIPE_GENERATION_METHOD_GUI = Registry.register(BuiltInRegistries.MENU, new ResourceLocation(RecipeGeneratorMod.MODID, "choosing_the_recipe_generation_method_gui"),
				new ExtendedScreenHandlerType<>(ChoosingTheRecipeGenerationMethodGUIMenu::new));
		ChoosingTheRecipeGenerationMethodGUIMenu.screenInit();
		CHOOSING_THE_RECIPE_GENERATING_METHOD_GUI_WITH_COMMAND = Registry.register(BuiltInRegistries.MENU, new ResourceLocation(RecipeGeneratorMod.MODID, "choosing_the_recipe_generating_method_gui_with_command"),
				new ExtendedScreenHandlerType<>(ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu::new));
		ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu.screenInit();
	}
}
