package com.recipe_generator.api.client.gui;

import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class RecipeGeneratorUI {
    public static final int BUTTON_WIDTH = 60;
    public static final int BUTTON_HEIGHT = 20;

    public static final int FIELD_WIDTH = 100;
    public static final int FIELD_HEIGHT = 20;

    public static Button createButton(String text, int x, int y, Runnable action) {
        return Button.builder(
            Component.literal(text),
            btn -> action.run()
        )
        .bounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT)
        .build();
    }
}
