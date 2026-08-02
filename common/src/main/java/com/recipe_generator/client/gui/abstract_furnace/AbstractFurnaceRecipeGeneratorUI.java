package com.recipe_generator.client.gui.abstract_furnace;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractFurnaceMenu;

import com.recipe_generator.api.client.gui.RecipeGeneratorUI;

public class AbstractFurnaceRecipeGeneratorUI<T extends AbstractFurnaceMenu> extends RecipeGeneratorUI {
    private final AbstractFurnaceScreen<T> screen;

    private EditBox cookingTimeField;
    private EditBox experienceField;

    public AbstractFurnaceRecipeGeneratorUI(AbstractFurnaceScreen<T> screen) {
        super(screen);
        this.screen = screen;
    }

    @Override
    public void init() {
        createFields();
        createButtons();
        createFurnaceFields();
    }

    private void createFurnaceFields() {
        cookingTimeField = addComponent(new EditBox(
            Minecraft.getInstance().font,
            centerX - 200,
            centerY + 5,
            FIELD_WIDTH,
            FIELD_HEIGHT,
            Component.literal("")
        ));
        cookingTimeField.setMaxLength(10);
        cookingTimeField.setHint(Component.literal("Time (ticks)"));

        experienceField = addComponent(new EditBox(
            Minecraft.getInstance().font,
            centerX - 200,
            centerY + 30,
            FIELD_WIDTH,
            FIELD_HEIGHT,
            Component.literal("")
        ));
        experienceField.setMaxLength(10);
        experienceField.setHint(Component.literal("XP"));
    }

    @Override
    protected void generate() {
        if (Minecraft.getInstance().player == null) return;
        sendMessage("§aNot implemented yet!");
    }

    @Override
    protected void save() {
        if (Minecraft.getInstance().player == null) return;
        sendMessage("§aNot implemented yet!");
    }
}
