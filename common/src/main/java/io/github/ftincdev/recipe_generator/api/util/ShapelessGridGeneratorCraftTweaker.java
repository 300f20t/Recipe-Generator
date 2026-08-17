package io.github.ftincdev.recipe_generator.api.util;

import io.github.ftincdev.recipe_generator.api.SlotsData;
import net.minecraft.world.item.ItemStack;

public class ShapelessGridGeneratorCraftTweaker {
    public String generate(SlotsData slots, int gridSize) {
        StringBuilder pattern = new StringBuilder();
        pattern.append("    ");

        boolean first = true;
        for (int i = 1; i <= gridSize; i++) {
            ItemStack item = slots.getItem(i);

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
