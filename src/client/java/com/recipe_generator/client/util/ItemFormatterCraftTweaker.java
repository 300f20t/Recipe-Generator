package com.recipe_generator.client.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;

public class ItemFormatterCraftTweaker {
    public static String format(ItemStack stack) {
        String id = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
        return "<item:" + id + ">";
    }

    public static String format(ItemStack stack, int count) {
        String formatted = format(stack);
        return formatted + " * " + count;
    }
}
