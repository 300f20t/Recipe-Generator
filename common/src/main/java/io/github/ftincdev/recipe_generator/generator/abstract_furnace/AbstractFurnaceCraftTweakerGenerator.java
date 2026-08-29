package io.github.ftincdev.recipe_generator.generator.abstract_furnace;

import java.util.Locale;

import io.github.ftincdev.recipe_generator.api.util.ItemFormatterCraftTweaker;
import io.github.ftincdev.recipe_generator.api.IVirtualSlot;
import io.github.ftincdev.recipe_generator.api.RecipeParams;
import net.minecraft.world.item.ItemStack;

class AbstractFurnaceCraftTweakerGenerator {
    protected String generate(IVirtualSlot[] slots, String name, RecipeParams params) {
        ItemStack inputItem = slots[0].getItem();
        ItemStack resultItem = slots[1].getItem();
        
        String input = ItemFormatterCraftTweaker.format(inputItem);
        String result = ItemFormatterCraftTweaker.format(resultItem, resultItem.getCount());
        
        String furnaceType = params.get("furnaceType", String.class);
        float experience = params.get("experience", Float.class);
        int cookingTime = params.get("cookingTime", Integer.class);
        
        return String.format(
            Locale.US,
            """
            %s.addRecipe("%s", %s, %s, %f, %d);
            """, furnaceType, name, result, input, experience, cookingTime);
    }
}
