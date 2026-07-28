package com.recipe_generator.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.ItemStack;

public class NameGenerator {
    public String generateName() {
        String baseName = "recipe_" + System.currentTimeMillis();   

        CraftingMenu menu = ((CraftingScreen) Minecraft.getInstance().screen).getMenu();
        ItemStack result = menu.slots.get(0).getItem();
        if (!result.isEmpty()) {
            String itemName = result.getItem().getDescriptionId().replace("block.minecraft.", "").replace("item.minecraft.", "");
            baseName = itemName + "_" + System.currentTimeMillis();
        }
    
        return baseName;
    }
}
