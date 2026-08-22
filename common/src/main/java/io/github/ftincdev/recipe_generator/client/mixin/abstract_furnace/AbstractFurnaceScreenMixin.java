package io.github.ftincdev.recipe_generator.client.mixin.abstract_furnace;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.ftincdev.recipe_generator.CommonClass;
import io.github.ftincdev.recipe_generator.client.gui.abstract_furnace.AbstractFurnaceRecipeGeneratorUI;
import io.github.ftincdev.recipe_generator.client.mixin.accessor.AbstractContainerScreenAccessor;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.inventory.BlastFurnaceScreen;
import net.minecraft.client.gui.screens.inventory.SmokerScreen;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

@Mixin(AbstractFurnaceScreen.class)
public class AbstractFurnaceScreenMixin<T extends AbstractFurnaceMenu> {
    private AbstractFurnaceRecipeGeneratorUI<T> recipeGeneratorUI;

    @SuppressWarnings("unchecked")
    @Inject(at = @At("RETURN"), method = "init")
    private void addUI(CallbackInfo ci) {
        if (CommonClass.isUIHidden) return;

        AbstractFurnaceScreen<T> screen = (AbstractFurnaceScreen<T>)(Object)this;
        AbstractFurnaceMenu menu = screen.getMenu();

        for (int i = 0; i < 3; i++) {
            menu.slots.get(i).set(ItemStack.EMPTY);
            Slot emptySlot = new Slot(new SimpleContainer(1), 0, -1000, -1000);
            menu.slots.set(i, emptySlot);
        }

        String furnaceType = "furnace";
        if (screen instanceof BlastFurnaceScreen) {
            furnaceType = "blastFurnace";
        } else if (screen instanceof SmokerScreen) {
            furnaceType = "smoker";
        }
        
        recipeGeneratorUI = new AbstractFurnaceRecipeGeneratorUI<T>(screen, furnaceType);
        recipeGeneratorUI.init();
        recipeGeneratorUI.addToScreen();
        recipeGeneratorUI.updateVisibility(screen.getRecipeBookComponent().isVisible());
        
        AbstractContainerScreenAccessor accessor = (AbstractContainerScreenAccessor) screen;
        recipeGeneratorUI.setPositions(accessor.getLeftPos(), accessor.getTopPos());
    }

    @SuppressWarnings("unchecked")
    @Inject(at = @At("HEAD"), method = "render")
    private void onRender(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if (recipeGeneratorUI == null) return;
        recipeGeneratorUI.renderVirtualSlots(guiGraphics);
        recipeGeneratorUI.updateVisibility(((AbstractFurnaceScreen<T>)(Object)this).getRecipeBookComponent().isVisible());
    }

    @Inject(at = @At("HEAD"), method = "keyPressed", cancellable = true)
    private void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (recipeGeneratorUI != null && (
            recipeGeneratorUI.keyPressed(keyCode, scanCode, modifiers) || 
            recipeGeneratorUI.furnaceKeyPressed(keyCode, scanCode, modifiers)
        )) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void onMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (CommonClass.isUIHidden) return;

        AbstractFurnaceScreen<?> screen = (AbstractFurnaceScreen<?>)(Object)this;
        AbstractContainerScreenAccessor accessor = (AbstractContainerScreenAccessor) screen;

        int leftPos = accessor.getLeftPos();
        int topPos = accessor.getTopPos();

        for (int i = 0; i < 3; i++) {
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
