package com.recipe_generator.client.platform;

import com.recipe_generator.platform.services.INameGenerator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.ItemStack;

public class NeoForgeNameGenerator implements INameGenerator {
    @Override
    public String generateName() {
        CraftingMenu menu = ((CraftingScreen) Minecraft.getInstance().screen).getMenu();
        ItemStack result = menu.slots.get(0).getItem();
        
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
