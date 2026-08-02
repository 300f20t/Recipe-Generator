package com.recipe_generator.generator.abstract_furnace;

import com.recipe_generator.api.RecipeParams;
import com.recipe_generator.api.SlotsData;
import com.recipe_generator.api.util.ItemFormatterCraftTweaker;

import net.minecraft.world.item.ItemStack;

class AbstractFurnaceCraftTweakerGenerator {
    protected String generate(SlotsData slots, String name, RecipeParams params) {
        ItemStack inputItem = slots.getItem(0);
        ItemStack resultItem = slots.getItem(2);
        
        String input = ItemFormatterCraftTweaker.format(inputItem, 1);
        String result = ItemFormatterCraftTweaker.format(resultItem, resultItem.getCount());
        
        String furnaceType = params.get("furnaceType", String.class);
        float experience = params.get("experience", Float.class);
        int cookingTime = params.get("cookingTime", Integer.class);
        
        return String.format("""
            %s.addRecipe("%s", %s, %s, %f, %d);
            """, furnaceType, name, result, input, experience, cookingTime);
    }
}
