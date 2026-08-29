package io.github.ftincdev.recipe_generator.api.util;

import io.github.ftincdev.recipe_generator.api.IVirtualSlot;
import net.minecraft.world.item.ItemStack;

public class ShapelessGridGeneratorCraftTweaker {
    public String generate(IVirtualSlot[] slots, int gridSize) {
        StringBuilder pattern = new StringBuilder();
        pattern.append("    ");

        boolean first = true;
        for (int i = 1; i <= gridSize; i++) {
            ItemStack item = slots[i].getItem();

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
