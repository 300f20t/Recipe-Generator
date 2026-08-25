package io.github.ftincdev.recipe_generator.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.item.ItemStack;

import io.github.ftincdev.recipe_generator.CommonClass;

@Mixin({CraftingMenu.class, AbstractFurnaceMenu.class})
public class QuickMoveMixin {
    
    @Inject(method = "quickMoveStack", at = @At("HEAD"), cancellable = true)
    private void onQuickMoveStack(Player player, int index, CallbackInfoReturnable<ItemStack> cir) {
        if (CommonClass.isUIHidden) return;
        cir.setReturnValue(ItemStack.EMPTY);
    }
}
