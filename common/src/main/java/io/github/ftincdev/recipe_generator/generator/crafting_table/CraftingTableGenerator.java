package io.github.ftincdev.recipe_generator.generator.crafting_table;

import io.github.ftincdev.recipe_generator.api.IVirtualSlot;
import io.github.ftincdev.recipe_generator.api.RecipeParams;
import io.github.ftincdev.recipe_generator.api.generator.block.IGenerator;

public class CraftingTableGenerator implements IGenerator {
    public enum RecipeType {
        SHAPED,
        SHAPELESS
    }

    public String generate(IVirtualSlot[] slots, String name, RecipeParams params) {
        return switch (params.get("type", RecipeType.class)) {
            case RecipeType.SHAPED -> new CraftingTableShapedCraftTweakerGenerator().generate(slots, name);
            case RecipeType.SHAPELESS -> new CraftingTableShapelessCraftTweakerGenerator().generate(slots, name);
            default -> new CraftingTableShapedCraftTweakerGenerator().generate(slots, name);
        };
    }
}
