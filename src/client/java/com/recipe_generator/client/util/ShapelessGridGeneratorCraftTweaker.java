package com.recipe_generator.client.util;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ShapelessGridGeneratorCraftTweaker {
    public String generate(NonNullList<Slot> slots, int gridSize) {
        StringBuilder pattern = new StringBuilder();

        pattern.append("    ");

        for (int i = 0; i <= gridSize; i++) {
            ItemStack item = slots.get(i).getItem();

            if (!item.isEmpty()) {
                pattern.append(ItemFormatterCraftTweaker.format(item));
                pattern.append(",");
            }
        }

        pattern.append("\n");

        return pattern.toString();
    }
}
