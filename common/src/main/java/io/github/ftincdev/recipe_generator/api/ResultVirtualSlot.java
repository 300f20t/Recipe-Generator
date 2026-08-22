package io.github.ftincdev.recipe_generator.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;

public class ResultVirtualSlot extends VirtualSlot {
    
    public ResultVirtualSlot(int x, int y) {
        super(x, y);
    }
    
    @Override
    public void render(GuiGraphics guiGraphics, int leftPos, int topPos) {
        int screenX = leftPos + x;
        int screenY = topPos + y;

        guiGraphics.fill(screenX - 1, screenY - 1, screenX + size + 1, screenY + size + 1, 0xFF8B8B8B);
        guiGraphics.fill(screenX, screenY, screenX + size, screenY + size, 0xFF2B2B2B);

        if (!item.isEmpty()) {
            guiGraphics.renderItem(item, screenX, screenY);
            guiGraphics.renderItemDecorations(Minecraft.getInstance().font, item, screenX, screenY);
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
}
