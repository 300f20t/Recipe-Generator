package com.recipe_generator.client.mixin;

import net.minecraft.client.gui.screens.inventory.CraftingScreen;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.recipe_generator.client.RecipeGeneratorClient;
import com.recipe_generator.client.gui.RecipeGeneratorUI;

@Mixin(CraftingScreen.class)
public class CraftingScreenMixin {
    
    private RecipeGeneratorUI recipeGeneratorUI;
    
    @Inject(at = @At("RETURN"), method = "init")
    private void addUI(CallbackInfo ci) {
        if (RecipeGeneratorClient.isUIHidden) return;
        
        CraftingScreen screen = (CraftingScreen)(Object)this;
        recipeGeneratorUI = new RecipeGeneratorUI(screen);
        recipeGeneratorUI.init();
        recipeGeneratorUI.addToScreen();
        recipeGeneratorUI.updateVisibility(screen.getRecipeBookComponent().isVisible());
    }

    @Inject(at = @At("HEAD"), method = "render")
    private void onRender(CallbackInfo ci) {
        if (recipeGeneratorUI == null) return;
        CraftingScreen screen = (CraftingScreen)(Object)this;
        recipeGeneratorUI.updateVisibility(screen.getRecipeBookComponent().isVisible());
    }

    @Inject(at = @At("HEAD"), method = "keyPressed", cancellable = true)
    private void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (recipeGeneratorUI != null && recipeGeneratorUI.keyPressed(keyCode, scanCode, modifiers)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "charTyped", cancellable = true)
    private void onCharTyped(char codePoint, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (recipeGeneratorUI != null && recipeGeneratorUI.charTyped(codePoint, modifiers)) {
            cir.setReturnValue(true);
        }
    }
}
