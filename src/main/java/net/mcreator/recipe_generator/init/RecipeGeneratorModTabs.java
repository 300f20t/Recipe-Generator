/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.recipe_generator.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.recipe_generator.RecipeGeneratorMod;

public class RecipeGeneratorModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RecipeGeneratorMod.MODID);
	public static final RegistryObject<CreativeModeTab> RECIPE_GENERATOR_CREATIVE_TAB = REGISTRY.register("recipe_generator_creative_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.recipe_generator.recipe_generator_creative_tab")).icon(() -> new ItemStack(Blocks.CRAFTING_TABLE)).displayItems((parameters, tabData) -> {
				tabData.accept(RecipeGeneratorModItems.RECIPE_EDITING_TOOL.get());
			}).build());
}