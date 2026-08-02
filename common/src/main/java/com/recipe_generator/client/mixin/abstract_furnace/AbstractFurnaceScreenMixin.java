package com.recipe_generator.client.mixin.abstract_furnace;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.recipe_generator.CommonClass;
import com.recipe_generator.client.gui.abstract_furnace.AbstractFurnaceRecipeGeneratorUI;

import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.inventory.BlastFurnaceScreen;
import net.minecraft.client.gui.screens.inventory.SmokerScreen;
import net.minecraft.world.inventory.AbstractFurnaceMenu;


@Mixin(AbstractFurnaceScreen.class)
public class AbstractFurnaceScreenMixin<T extends AbstractFurnaceMenu> {
    private AbstractFurnaceRecipeGeneratorUI<T> recipeGeneratorUI;

    @SuppressWarnings("unchecked")
    @Inject(at = @At("RETURN"), method = "init")
    private void addUI(CallbackInfo ci) {
        if (CommonClass.isUIHidden) return;

        AbstractFurnaceScreen<T> screen = (AbstractFurnaceScreen<T>)(Object)this;

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
    }

    @SuppressWarnings("unchecked")
    @Inject(at = @At("HEAD"), method = "render")
    private void onRender(CallbackInfo ci) {
        if (recipeGeneratorUI == null) return;
        AbstractFurnaceScreen<T> screen = (AbstractFurnaceScreen<T>)(Object)this;
        recipeGeneratorUI.updateVisibility(screen.getRecipeBookComponent().isVisible());
    }
}
