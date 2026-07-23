package com.recipe_generator.client.generator.crafting_table;

import net.minecraft.world.inventory.CraftingMenu;

public class Generator {
    public String generate(CraftingMenu menu, String name){
        return new CraftTweakerGenerator().generate(menu, name);
    }
}
