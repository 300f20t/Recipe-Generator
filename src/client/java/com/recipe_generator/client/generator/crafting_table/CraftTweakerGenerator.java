package com.recipe_generator.client.generator.crafting_table;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.recipe_generator.client.util.ItemFormatter;

public class CraftTweakerGenerator {
    public String generateShaped(NonNullList<Slot> slots, String name) {
        ItemStack resultItem = slots.get(0).getItem();
        String result = ItemFormatter.formatCraftTweaker(resultItem, resultItem.getCount());

        StringBuilder pattern = new StringBuilder();
        for (int row = 0; row < 3; row++) {
            pattern.append("    [");
            for (int col = 0; col < 3; col++) {
                int index = row * 3 + col + 1;
                pattern.append(ItemFormatter.formatCraftTweaker(slots.get(index).getItem()));
                if (col < 2) pattern.append(", ");
            }
            pattern.append("]");
            if (row < 2) {
                pattern.append(",");
                pattern.append("\n");
            }
        }

        return String.format("""
            craftingTable.addShaped("%s", %s, [
            %s
            ]);
            """, name, result, pattern.toString());
    }
}
