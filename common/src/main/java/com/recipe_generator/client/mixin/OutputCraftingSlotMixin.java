package com.recipe_generator.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.recipe_generator.CommonClass;
import com.recipe_generator.CustomOutputSlot;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;

@Mixin(CraftingMenu.class)
public class OutputCraftingSlotMixin {

    @Shadow
    private CraftingContainer craftSlots;
    
    @Shadow
    private ResultContainer resultSlots;
    
    @Inject(at = @At("RETURN"), method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/inventory/ContainerLevelAccess;)V")
    private void addOutputSlot(int i, Inventory inv, ContainerLevelAccess access, CallbackInfo ci) {
        CraftingMenu menu = (CraftingMenu)(Object)this;
        if (!CommonClass.isUIHidden) {
            Slot newSlot = new CustomOutputSlot(
                this.resultSlots,
                0,
                124,
                35
            );
        
            menu.slots.set(0, newSlot);
        }
    }
}
