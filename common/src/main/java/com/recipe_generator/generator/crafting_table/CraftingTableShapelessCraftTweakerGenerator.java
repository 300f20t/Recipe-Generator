package com.recipe_generator.generator.crafting_table;

import com.recipe_generator.api.SlotsData;
import com.recipe_generator.util.ItemFormatterCraftTweaker;
import com.recipe_generator.util.ShapelessGridGeneratorCraftTweaker;

import net.minecraft.world.item.ItemStack;

public class CraftingTableShapelessCraftTweakerGenerator {
    public String generate(SlotsData slots, String name) {
        ItemStack resultItem = slots.getItem(0);

        String result = ItemFormatterCraftTweaker.format(resultItem, resultItem.getCount());
        String pattern = new ShapelessGridGeneratorCraftTweaker().generate(slots, 9);

        return String.format("""
            craftingTable.addShapeless("%s", %s, [
            %s
            ]);
            """, name, result, pattern);
    }
}
