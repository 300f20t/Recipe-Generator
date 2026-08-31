package io.github.ftincdev.recipe_generator.client.mixin.crafting_table;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.ftincdev.recipe_generator.CommonClass;
import io.github.ftincdev.recipe_generator.client.gui.crafting_table.CraftingTableRecipeGeneratorUI;
import io.github.ftincdev.recipe_generator.client.mixin.accessor.AbstractContainerScreenAccessor;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import io.github.ftincdev.recipe_generator.client.mixin.accessor.ScreenAccessor;

@Mixin(CraftingScreen.class)
public class CraftingScreenMixin {
    
    private CraftingTableRecipeGeneratorUI recipeGeneratorUI;
    
    @Inject(at = @At("RETURN"), method = "init")
    private void addUI(CallbackInfo ci) {
        if (CommonClass.isUIHidden) return;
        
        CraftingScreen screen = (CraftingScreen)(Object)this;
        CraftingMenu menu = screen.getMenu();

        if (screen.getRecipeBookComponent().isVisible()) {
            screen.getRecipeBookComponent().toggleVisibility();
        }
        
        AbstractContainerScreenAccessor accessor = (AbstractContainerScreenAccessor) screen;

        accessor.setLeftPos(screen.getRecipeBookComponent().updateScreenPosition(screen.width, accessor.getImageWidth()));

        ScreenAccessor screenAccessor = (ScreenAccessor) screen;
        for (Renderable renderable : screenAccessor.getRenderables()) {
            if (renderable instanceof ImageButton button) {
                button.visible = false;
                button.active = false;
            }
        }

        for (int i = 0; i < 10; i++) {
            menu.slots.get(i).set(ItemStack.EMPTY);
            Slot emptySlot = new Slot(new SimpleContainer(1), 0, -1000, -1000);
            menu.slots.set(i, emptySlot);
        }
        
        recipeGeneratorUI = new CraftingTableRecipeGeneratorUI(screen);
        recipeGeneratorUI.init();
        recipeGeneratorUI.addToScreen();
        recipeGeneratorUI.setPositions(accessor.getLeftPos(), accessor.getTopPos());
    }

    @Inject(at = @At("TAIL"), method = "render")
    private void onRenderTail(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if (recipeGeneratorUI != null) {
            recipeGeneratorUI.renderVirtualSlots(guiGraphics, mouseX, mouseY);
        }
    }

    @Inject(at = @At("HEAD"), method = "keyPressed", cancellable = true)
    private void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (recipeGeneratorUI != null && recipeGeneratorUI.keyPressed(keyCode, scanCode, modifiers)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void onMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (CommonClass.isUIHidden) return;

        CraftingScreen screen = (CraftingScreen)(Object)this;
        AbstractContainerScreenAccessor accessor = (AbstractContainerScreenAccessor) screen;

        int leftPos = accessor.getLeftPos();
        int topPos = accessor.getTopPos();

        for (int i = 0; i < 10; i++) {
            Slot slot = screen.getMenu().slots.get(i);
            int slotX = leftPos + slot.x;
            int slotY = topPos + slot.y;

            if (mouseX >= slotX && mouseX <= slotX + 16 && mouseY >= slotY && mouseY <= slotY + 16) {
                cir.setReturnValue(true);
                return;
            }
        }

        if (recipeGeneratorUI != null && recipeGeneratorUI.handleVirtualSlotClick(mouseX, mouseY)) {
            cir.setReturnValue(true);
        }
    }
}
