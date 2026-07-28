package com.recipe_generator.client.generator.crafting_table;

import com.recipe_generator.client.util.ItemFormatterCraftTweaker;
import com.recipe_generator.client.util.ShapelessGridGeneratorCraftTweaker;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class CraftingTableShapelessCraftTweakerGenerator {
    public String generate(NonNullList<Slot> slots, String name) {
        ItemStack resultItem = slots.get(0).getItem();

        String result = ItemFormatterCraftTweaker.format(resultItem, resultItem.getCount());
        String pattern = new ShapelessGridGeneratorCraftTweaker().generate(slots, 9);

        return String.format("""
            craftingTable.addShapeless("%s", %s, [
            %s
            ]);
            """, name, result, pattern);
    }
}
