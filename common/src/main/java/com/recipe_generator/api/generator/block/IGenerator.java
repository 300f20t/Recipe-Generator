package com.recipe_generator.api.generator.block;

import com.recipe_generator.api.SlotsData;

public interface IGenerator {
    default String generate(SlotsData slots, String name, String type) {
        return generate(slots, name);
    }

    String generate(SlotsData slots, String name);
}
