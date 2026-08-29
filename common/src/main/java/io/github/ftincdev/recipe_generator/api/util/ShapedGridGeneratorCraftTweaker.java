package io.github.ftincdev.recipe_generator.api.util;

import io.github.ftincdev.recipe_generator.api.IVirtualSlot;

public class ShapedGridGeneratorCraftTweaker {
   public String generate(IVirtualSlot[] slots, int gridSizeX, int gridSizeY) {
        StringBuilder pattern = new StringBuilder();

        for (int row = 0; row < gridSizeY; row++) {
            pattern.append("    [");
            for (int col = 0; col < gridSizeX; col++) {
                int index = row * gridSizeX + col + 1;
                pattern.append(ItemFormatterCraftTweaker.format(slots[index].getItem()));
                if (col < gridSizeX - 1) pattern.append(", ");
            }
            pattern.append("]");
            if (row < gridSizeY - 1) {
                pattern.append(",");
                pattern.append("\n");
            }
        }

        return(pattern.toString());
   } 
}
