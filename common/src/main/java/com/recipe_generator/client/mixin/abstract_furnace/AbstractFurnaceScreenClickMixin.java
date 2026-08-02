package com.recipe_generator.client.mixin.abstract_furnace;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.recipe_generator.CommonClass;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import java.lang.reflect.Field;

@Mixin(AbstractFurnaceScreen.class)
public class AbstractFurnaceScreenClickMixin {
    
    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void onMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (CommonClass.isUIHidden) return;
        
        AbstractFurnaceScreen<?> screen = (AbstractFurnaceScreen<?>)(Object)this;
        
        try {
            Field leftPosField = AbstractContainerScreen.class.getDeclaredField("leftPos");
            Field topPosField = AbstractContainerScreen.class.getDeclaredField("topPos");
            leftPosField.setAccessible(true);
            topPosField.setAccessible(true);
            
            int leftPos = (int) leftPosField.get(screen);
            int topPos = (int) topPosField.get(screen);
            
            Slot resultSlot = screen.getMenu().slots.get(2);
            int slotX = leftPos + resultSlot.x;
            int slotY = topPos + resultSlot.y;
            
            if (mouseX >= slotX && mouseX <= slotX + 16 && mouseY >= slotY && mouseY <= slotY + 16) {
                Player player = Minecraft.getInstance().player;
                if (player == null) return;
                
                ItemStack carried = player.containerMenu.getCarried();
                
                if (resultSlot.hasItem() && carried.isEmpty()) {
                    player.containerMenu.setCarried(resultSlot.getItem().copy());
                    resultSlot.set(ItemStack.EMPTY);
                    cir.setReturnValue(true);
                } else if (!carried.isEmpty() && !resultSlot.hasItem()) {
                    resultSlot.set(carried.copy());
                    player.containerMenu.setCarried(ItemStack.EMPTY);
                    cir.setReturnValue(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
