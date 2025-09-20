/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.recipe_generator.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import net.mcreator.recipe_generator.item.RecipeEditingToolItem;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

public class RecipeGeneratorModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, RecipeGeneratorMod.MODID);
	public static final RegistryObject<Item> RECIPE_EDITING_TOOL = REGISTRY.register("recipe_editing_tool", () -> new RecipeEditingToolItem());
	// Start of user code block custom items
	// End of user code block custom items
}