package io.github.ftincdev.recipe_generator.generator.crafting_table;

import io.github.ftincdev.recipe_generator.api.util.ItemFormatterCraftTweaker;

import io.github.ftincdev.recipe_generator.api.SlotsData;
import io.github.ftincdev.recipe_generator.api.util.ShapelessGridGeneratorCraftTweaker;
import net.minecraft.world.item.ItemStack;

class CraftingTableShapelessCraftTweakerGenerator {
    protected String generate(SlotsData slots, String name) {
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
