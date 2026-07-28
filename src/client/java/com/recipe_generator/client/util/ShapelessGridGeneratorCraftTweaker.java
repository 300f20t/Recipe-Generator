package com.recipe_generator.client.util;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ShapelessGridGeneratorCraftTweaker {
    public String generate(NonNullList<Slot> slots, int gridSize) {
        StringBuilder pattern = new StringBuilder();
        pattern.append("    ");

        boolean first = true;
        for (int i = 1; i <= gridSize; i++) {
            ItemStack item = slots.get(i).getItem();

            if (!item.isEmpty()) {
                if (!first) {
                    pattern.append(", ");
                }
                pattern.append(ItemFormatterCraftTweaker.format(item));
                first = false;
            }
        }

        return pattern.toString();
    }
}