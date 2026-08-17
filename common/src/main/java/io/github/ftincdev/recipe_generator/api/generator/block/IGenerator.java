package io.github.ftincdev.recipe_generator.api.generator.block;

import io.github.ftincdev.recipe_generator.api.RecipeParams;
import io.github.ftincdev.recipe_generator.api.SlotsData;

public interface IGenerator { 
    public String generate(SlotsData slots, String name, RecipeParams params);
}
