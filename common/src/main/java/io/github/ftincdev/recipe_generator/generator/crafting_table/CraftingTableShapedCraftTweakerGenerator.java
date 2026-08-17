package io.github.ftincdev.recipe_generator.generator.crafting_table;

import io.github.ftincdev.recipe_generator.api.util.ItemFormatterCraftTweaker;

import io.github.ftincdev.recipe_generator.api.SlotsData;
import io.github.ftincdev.recipe_generator.api.util.ShapedGridGeneratorCraftTweaker;
import net.minecraft.world.item.ItemStack;

class CraftingTableShapedCraftTweakerGenerator {
    protected String generate(SlotsData slots, String name) {
        ItemStack resultItem = slots.getItem(0);
        String result = ItemFormatterCraftTweaker.format(resultItem, resultItem.getCount());
        String pattern = new ShapedGridGeneratorCraftTweaker().generate(slots, 3, 3);

        return String.format("""
            craftingTable.addShaped("%s", %s, [
            %s
            ]);
            """, name, result, pattern);
    }
}
