package com.recipe_generator.generator.abstract_furnace;

import com.recipe_generator.api.RecipeParams;
import com.recipe_generator.api.SlotsData;
import com.recipe_generator.api.generator.block.IGenerator;

public class AbstractFurnaceGenerator implements IGenerator {
    public String generate(SlotsData slots, String name, RecipeParams params) {
        return new AbstractFurnaceCraftTweakerGenerator().generate(slots, name, params);
    }
}
