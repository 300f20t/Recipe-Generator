package com.recipe_generator.generator.crafting_table;

import com.recipe_generator.api.RecipeParams;
import com.recipe_generator.api.SlotsData;
import com.recipe_generator.api.generator.block.IGenerator;

public class CraftingTableGenerator implements IGenerator {
    public enum RecipeType {
        SHAPED,
        SHAPELESS
    }

    public String generate(SlotsData slots, String name, RecipeParams params) {
        return switch (params.get("type", RecipeType.class)) {
            case RecipeType.SHAPED -> new CraftingTableShapedCraftTweakerGenerator().generate(slots, name);
            case RecipeType.SHAPELESS -> new CraftingTableShapelessCraftTweakerGenerator().generate(slots, name);
            default -> new CraftingTableShapedCraftTweakerGenerator().generate(slots, name);
        };
    }
}
