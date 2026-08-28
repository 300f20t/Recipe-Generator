package io.github.ftincdev.recipe_generator.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class VirtualSlot implements IVirtualSlot {
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
    
        int borderColor = isHovered ? 0xFFFFFFFF : 0xFF8B8B8B;
        int bgColor = isHovered ? 0x6633BBFF : 0xFF2B2B2B;
    
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
