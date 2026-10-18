package io.github.ftincdev.recipe_generator.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class VirtualSlot implements IVirtualSlot {
    protected static final int BORDER_COLOR = 0xFF8B8B8B;
    protected static final int BACKGROUND_COLOR = 0xFF2B2B2B;

    protected static final int COLOR_HOVERED = 0x6633BBFF;

    protected static final int BASE_SIZE = 16;
    protected static final int SIZE = BASE_SIZE;
    protected static final int OFFSET = (SIZE - BASE_SIZE) / 2;

    protected ItemStack item = ItemStack.EMPTY;
    protected final int x;
    protected final int y;

    public VirtualSlot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int leftPos, int topPos, boolean isHovered) {
        int screenX = leftPos + x - OFFSET;
        int screenY = topPos + y - OFFSET;
    
        guiGraphics.fill(screenX - 1, screenY - 1, screenX + SIZE + 1, screenY + SIZE + 1, BORDER_COLOR);
        guiGraphics.fill(screenX, screenY, screenX + SIZE, screenY + SIZE, BACKGROUND_COLOR);

        if (isHovered) {
            guiGraphics.fill(screenX, screenY, screenX + SIZE, screenY + SIZE, COLOR_HOVERED);
        }
    
        if (!item.isEmpty()) {
            guiGraphics.renderItem(item, screenX, screenY);
        }
    }

    @Override
    public boolean handleClick(double mouseX, double mouseY, int leftPos, int topPos) {
        int screenX = leftPos + x;
        int screenY = topPos + y;

        if (mouseX >= screenX && mouseX <= screenX + SIZE &&
            mouseY >= screenY && mouseY <= screenY + SIZE) {

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
