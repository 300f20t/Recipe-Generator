package io.github.ftincdev.recipe_generator.api.generator.block;

import io.github.ftincdev.recipe_generator.api.IVirtualSlot;
import io.github.ftincdev.recipe_generator.api.RecipeParams;

public interface IGenerator { 
    public String generate(IVirtualSlot[] slots, String name, RecipeParams params);
}
