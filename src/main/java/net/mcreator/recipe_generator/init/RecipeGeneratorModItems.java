/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.recipe_generator.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;

import net.minecraft.world.item.Item;

import net.mcreator.recipe_generator.item.RecipeEditingToolItem;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

public class RecipeGeneratorModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(RecipeGeneratorMod.MODID);
	public static final DeferredItem<Item> RECIPE_EDITING_TOOL;
	static {
		RECIPE_EDITING_TOOL = REGISTRY.register("recipe_editing_tool", RecipeEditingToolItem::new);
	}
	// Start of user code block custom items
	// End of user code block custom items
}