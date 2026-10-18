package io.github.ftincdev.recipe_generator.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;

public class ResultVirtualSlot extends VirtualSlot {
    private final int size = 24;
    
    public ResultVirtualSlot(int x, int y) {
        super(x, y);
    }
    
    @Override
    public void render(GuiGraphics guiGraphics, int leftPos, int topPos, boolean isHovered) {
        int screenX = leftPos + x - 4;
        int screenY = topPos + y - 4;
    
        int borderColor = isHovered ? BORDER_COLOR_HOVERED : BORDER_COLOR;
        int bgColor = isHovered ? BACKGROUND_COLOR_HOVERED : BACKGROUND_COLOR;
    
        guiGraphics.fill(screenX - 1, screenY - 1, screenX + size + 1, screenY + size + 1, borderColor);
        guiGraphics.fill(screenX, screenY, screenX + size, screenY + size, bgColor);
    
        if (!item.isEmpty()) {
            guiGraphics.renderItem(item, screenX + 4, screenY + 4);
            guiGraphics.renderItemDecorations(Minecraft.getInstance().font, item, screenX + 4, screenY + 4);
        }
    }
    
    @Override
    public boolean handleClick(double mouseX, double mouseY, int leftPos, int topPos) {
        int screenX = leftPos + x - 4;
        int screenY = topPos + y - 4;

        if (mouseX >= screenX && mouseX <= screenX + size &&
            mouseY >= screenY && mouseY <= screenY + size) {

            Player player = Minecraft.getInstance().player;
            if (player == null) return false;

            item = player.containerMenu.getCarried().copy();
            return true;
        }
        return false;
    }
}
