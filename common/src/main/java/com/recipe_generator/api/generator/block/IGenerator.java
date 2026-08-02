package com.recipe_generator.api.generator.block;

import com.recipe_generator.api.RecipeParams;
import com.recipe_generator.api.SlotsData;

public interface IGenerator { 
    public String generate(SlotsData slots, String name, RecipeParams params);
}
