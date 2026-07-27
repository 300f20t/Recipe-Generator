package com.recipe_generator.client.generator.crafting_table;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.recipe_generator.client.util.ShapedGridGeneratorCraftTweaker;
import com.recipe_generator.client.util.ItemFormatterCraftTweaker;

public class CraftingTableShapedCraftTweakerGenerator {
    public String generate(NonNullList<Slot> slots, String name) {
        ItemStack resultItem = slots.get(0).getItem();

        String result = ItemFormatterCraftTweaker.format(resultItem, resultItem.getCount());
        String pattern = new ShapedGridGeneratorCraftTweaker().generate(slots, 3, 3);

        return String.format("""
            craftingTable.addShaped("%s", %s, [
            %s
            ]);
            """, name, result, pattern);
    }
}
