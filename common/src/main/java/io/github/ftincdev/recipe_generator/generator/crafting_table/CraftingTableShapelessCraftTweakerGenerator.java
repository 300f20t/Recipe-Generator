package io.github.ftincdev.recipe_generator.generator.crafting_table;

import io.github.ftincdev.recipe_generator.api.util.ItemFormatterCraftTweaker;
import io.github.ftincdev.recipe_generator.api.IVirtualSlot;
import io.github.ftincdev.recipe_generator.api.util.ShapelessGridGeneratorCraftTweaker;
import net.minecraft.world.item.ItemStack;

class CraftingTableShapelessCraftTweakerGenerator {
    protected String generate(IVirtualSlot[] slots, String name) {
        ItemStack resultItem = slots[0].getItem();

        String result = ItemFormatterCraftTweaker.format(resultItem, resultItem.getCount());
        String pattern = new ShapelessGridGeneratorCraftTweaker().generate(slots, 9);

        return String.format("""
            craftingTable.addShapeless("%s", %s, [
            %s
            ]);
            """, name, result, pattern);
    }
}
