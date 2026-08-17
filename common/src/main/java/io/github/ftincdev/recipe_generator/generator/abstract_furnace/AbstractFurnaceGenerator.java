package io.github.ftincdev.recipe_generator.generator.abstract_furnace;

import io.github.ftincdev.recipe_generator.api.RecipeParams;
import io.github.ftincdev.recipe_generator.api.SlotsData;
import io.github.ftincdev.recipe_generator.api.generator.block.IGenerator;

public class AbstractFurnaceGenerator implements IGenerator {
    public String generate(SlotsData slots, String name, RecipeParams params) {
        return new AbstractFurnaceCraftTweakerGenerator().generate(slots, name, params);
    }
}
