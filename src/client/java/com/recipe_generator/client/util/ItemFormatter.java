package com.recipe_generator.client.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;

public class ItemFormatter {
    public static String formatCraftTweaker(ItemStack stack) {
        String id = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
        return "<item:" + id + ">";
    }

    public static String formatCraftTweaker(ItemStack stack, int count) {
        String formatted = formatCraftTweaker(stack);
        return formatted + " * " + count;
    }
}
