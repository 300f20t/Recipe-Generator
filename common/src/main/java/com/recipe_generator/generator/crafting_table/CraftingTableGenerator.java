package com.recipe_generator.generator.crafting_table;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.Slot;

public class CraftingTableGenerator {
    public enum RecipeType {
        SHAPED,
        SHAPELESS
    }

    public String generate(NonNullList<Slot> slots, String name, RecipeType type) {
        return switch (type) {
            case SHAPED -> new CraftingTableShapedCraftTweakerGenerator().generate(slots, name);
            case SHAPELESS -> new CraftingTableShapelessCraftTweakerGenerator().generate(slots, name);
        };
    }
}
