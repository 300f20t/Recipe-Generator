package com.recipe_generator.generator.crafting_table;

import com.recipe_generator.api.SlotsData;
import com.recipe_generator.api.generator.block.IGenerator;

public class CraftingTableGenerator implements IGenerator {

    public String generate(SlotsData slots, String name) {
        return generate(slots, name, "shaped");
    }

    public String generate(SlotsData slots, String name, String type) {
        return switch (type) {
            case "shaped" -> new CraftingTableShapedCraftTweakerGenerator().generate(slots, name);
            case "shapeless" -> new CraftingTableShapelessCraftTweakerGenerator().generate(slots, name);
            default -> new CraftingTableShapedCraftTweakerGenerator().generate(slots, name);
        };
    }
}
