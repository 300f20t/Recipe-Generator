package com.recipe_generator.client.util;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.Slot;

public class GridGenerator {
   public String generate(NonNullList<Slot> slots, int gridSizeX, int gridSizeY) {
        StringBuilder pattern = new StringBuilder();

        for (int row = 0; row < gridSizeY; row++) {
            pattern.append("    [");
            for (int col = 0; col < gridSizeX; col++) {
                int index = row * gridSizeX + col + 1;
                pattern.append(ItemFormatter.formatCraftTweaker(slots.get(index).getItem()));
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
