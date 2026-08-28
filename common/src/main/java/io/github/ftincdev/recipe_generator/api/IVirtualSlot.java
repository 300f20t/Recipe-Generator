package io.github.ftincdev.recipe_generator.api;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;

public interface IVirtualSlot {
    
    void render(GuiGraphics guiGraphics, int leftPos, int topPos, boolean isHovered);
    
    boolean handleClick(double mouseX, double mouseY, int leftPos, int topPos);
    
    ItemStack getItem();
    
    boolean isEmpty();
    
    void clear();
    
    int getX();
    
    int getY();
}
