package com.recipe_generator.api.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.item.ItemStack;

public class NameGenerator {
    public static String generateName() {
        Screen screen = Minecraft.getInstance().screen;
        
        if (screen instanceof CraftingScreen) {
            CraftingMenu menu = ((CraftingScreen) screen).getMenu();
            ItemStack result = menu.slots.get(0).getItem();
            return generateNameFromItem(result);
        }
        
        if (screen instanceof AbstractContainerScreen<?>) {
            AbstractContainerScreen<?> containerScreen = (AbstractContainerScreen<?>) screen;
            if (containerScreen.getMenu() instanceof AbstractFurnaceMenu) {
                AbstractFurnaceMenu menu = (AbstractFurnaceMenu) containerScreen.getMenu();
                if (menu.slots.size() > 2) {
                    ItemStack result = menu.slots.get(2).getItem();
                    return generateNameFromItem(result);
                }
            }
        }
        
        return "recipe_" + System.currentTimeMillis();
    }
    
    private static String generateNameFromItem(ItemStack result) {
        String baseName = "recipe_" + System.currentTimeMillis();
        if (!result.isEmpty()) {
            String itemName = result.getItem().getDescriptionId()
                .replace("block.minecraft.", "")
                .replace("item.minecraft.", "");
            baseName = itemName + "_" + System.currentTimeMillis();
        }
        return baseName;
    }
}
