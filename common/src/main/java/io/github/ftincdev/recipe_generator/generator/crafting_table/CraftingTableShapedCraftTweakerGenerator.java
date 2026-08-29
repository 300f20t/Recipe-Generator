package io.github.ftincdev.recipe_generator.generator.crafting_table;

import io.github.ftincdev.recipe_generator.api.IVirtualSlot;
import io.github.ftincdev.recipe_generator.api.util.ItemFormatterCraftTweaker;

import io.github.ftincdev.recipe_generator.api.util.ShapedGridGeneratorCraftTweaker;
import net.minecraft.world.item.ItemStack;

class CraftingTableShapedCraftTweakerGenerator {
    protected String generate(IVirtualSlot[] slots, String name) {
        ItemStack resultItem = slots[0].getItem();
        String result = ItemFormatterCraftTweaker.format(resultItem, resultItem.getCount());
        String pattern = new ShapedGridGeneratorCraftTweaker().generate(slots, 3, 3);

        return String.format("""
            craftingTable.addShaped("%s", %s, [
            %s
            ]);
            """, name, result, pattern);
    }
}
