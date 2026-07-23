package com.recipe_generator.client.generator.crafting_table;

import net.minecraft.world.inventory.CraftingMenu;

public class CraftTweakerGenerator {
    public String generate(CraftingMenu menu, String name) {
        return String.format("""
            craftingTable.addShaped("%s", <item:minecraft:arrow>, [
                [<item:minecraft:air>, <item:minecraft:air>, <item:minecraft:air>],
                [<item:minecraft:air>, <item:minecraft:air>, <item:minecraft:air>],
                [<item:minecraft:apple>, <item:minecraft:air>, <item:minecraft:air>]]);
            """, name);
    }
}
