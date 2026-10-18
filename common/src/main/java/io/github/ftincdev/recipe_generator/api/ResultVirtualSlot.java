package io.github.ftincdev.recipe_generator.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;

public class ResultVirtualSlot extends VirtualSlot {
    private static final int SIZE = 24;
    private static final int OFFSET = (SIZE - BASE_SIZE) / 2;
    
    public ResultVirtualSlot(int x, int y) {
        super(x, y);
    }
    
    @Override
    public void render(GuiGraphics guiGraphics, int leftPos, int topPos, boolean isHovered) {
        int screenX = leftPos + x - OFFSET;
        int screenY = topPos + y - OFFSET;
    
        int borderColor = BORDER_COLOR;
        int bgColor = BACKGROUND_COLOR;
    
        guiGraphics.fill(screenX - 1, screenY - 1, screenX + SIZE + 1, screenY + SIZE + 1, borderColor);
        guiGraphics.fill(screenX, screenY, screenX + SIZE, screenY + SIZE, bgColor);

        if (isHovered) {
            guiGraphics.fill(screenX + OFFSET, screenY + OFFSET, screenX + BASE_SIZE + OFFSET, screenY + BASE_SIZE + OFFSET, COLOR_HOVERED);
        }
    
        if (!item.isEmpty()) {
            guiGraphics.renderItem(item, screenX + OFFSET, screenY + OFFSET);
            guiGraphics.renderItemDecorations(Minecraft.getInstance().font, item, screenX + OFFSET, screenY + OFFSET);
        }
    }
    
    @Override
    public boolean handleClick(double mouseX, double mouseY, int leftPos, int topPos) {
        int screenX = leftPos + x - OFFSET;
        int screenY = topPos + y - OFFSET;

        if (mouseX >= screenX && mouseX <= screenX + SIZE &&
            mouseY >= screenY && mouseY <= screenY + SIZE) {

            Player player = Minecraft.getInstance().player;
            if (player == null) return false;

            item = player.containerMenu.getCarried().copy();
            return true;
        }
        return false;
    }
}
