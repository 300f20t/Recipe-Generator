package com.recipe_generator.client.gui.abstract_furnace;

import java.util.ArrayList;
import java.util.List;

import com.recipe_generator.CommonClass;
import com.recipe_generator.api.client.gui.RecipeGeneratorUI;
import com.recipe_generator.client.mixin.accessor.ScreenAccessor;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.world.inventory.AbstractFurnaceMenu;

public class AbstractFurnaceRecipeGeneratorUI<T extends AbstractFurnaceMenu> {
    private final AbstractFurnaceScreen<T> screen;
    private final List<Button> buttons = new ArrayList<>();
    private final int centerX;
    private final int centerY;

    public AbstractFurnaceRecipeGeneratorUI(AbstractFurnaceScreen<T> screen) {
        this.screen = screen;
        this.centerX = screen.width / 2;
        this.centerY = screen.height / 2;
    }

    public void init() {
        createButtons();
    }

    private void createButtons() {
        buttons.add(RecipeGeneratorUI.createButton("Close", centerX + 95, centerY + 55, () -> screen.onClose()));
    }

    public void addToScreen() {
        ScreenAccessor accessor = (ScreenAccessor) screen;
        List<Renderable> renderables = accessor.getRenderables();
        List<GuiEventListener> children = accessor.getChildren();
        
        for (Button button : buttons) {
            renderables.add(button);
            children.add(button);
        }
    }

    public void updateVisibility(boolean bookVisible) {
        boolean shouldShow = !CommonClass.isUIHidden && !bookVisible;
        for (Button btn : buttons) {
            btn.visible = shouldShow;
            btn.active = shouldShow;
        }
    }
}
