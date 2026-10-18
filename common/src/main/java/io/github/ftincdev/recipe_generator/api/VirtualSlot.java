package io.github.ftincdev.recipe_generator.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class VirtualSlot implements IVirtualSlot {
    protected static final int BORDER_COLOR = 0xFF8B8B8B;
    protected static final int BORDER_COLOR_HOVERED = 0xFFFFFFFF;

    protected static final int BACKGROUND_COLOR = 0xFF2B2B2B;
    protected static final int BACKGROUND_COLOR_HOVERED = 0xFF5FDAE8;

    protected ItemStack item = ItemStack.EMPTY;
    protected final int x;
    protected final int y;
    protected final int size = 16;

    public VirtualSlot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int leftPos, int topPos, boolean isHovered) {
        int screenX = leftPos + x;
        int screenY = topPos + y;
    
        int borderColor = isHovered ? BORDER_COLOR_HOVERED : BORDER_COLOR;
        int bgColor = isHovered ? BACKGROUND_COLOR_HOVERED : BACKGROUND_COLOR;
    
        guiGraphics.fill(screenX - 1, screenY - 1, screenX + size + 1, screenY + size + 1, borderColor);
        guiGraphics.fill(screenX, screenY, screenX + size, screenY + size, bgColor);
    
        if (!item.isEmpty()) {
            guiGraphics.renderItem(item, screenX, screenY);
        }
    }

    @Override
    public boolean handleClick(double mouseX, double mouseY, int leftPos, int topPos) {
        int screenX = leftPos + x;
        int screenY = topPos + y;

        if (mouseX >= screenX && mouseX <= screenX + size &&
            mouseY >= screenY && mouseY <= screenY + size) {

            Player player = Minecraft.getInstance().player;
            if (player == null) return false;

            item = player.containerMenu.getCarried().copy();
            return true;
        }
        return false;
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public boolean isEmpty() {
        return item.isEmpty();
    }

    @Override
    public void clear() {
        item = ItemStack.EMPTY;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
