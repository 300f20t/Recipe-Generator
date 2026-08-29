package io.github.ftincdev.recipe_generator.api.client.gui;

import java.util.ArrayList;
import java.util.List;

import io.github.ftincdev.recipe_generator.Constants.GenerationMethod;

import io.github.ftincdev.recipe_generator.CommonClass;
import io.github.ftincdev.recipe_generator.api.util.NameGenerator;
import io.github.ftincdev.recipe_generator.client.mixin.accessor.ScreenAccessor;
import io.github.ftincdev.recipe_generator.platform.Services;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;

public abstract class RecipeGeneratorUI {
    public static final int BUTTON_WIDTH = 60;
    public static final int BUTTON_HEIGHT = 20;
    public static final int FIELD_WIDTH = 100;
    public static final int FIELD_HEIGHT = 20;

    protected final AbstractContainerScreen<?> screen;
    protected final List<Button> buttons = new ArrayList<>();
    protected final List<Renderable> renderables = new ArrayList<>();
    protected final List<GuiEventListener> children = new ArrayList<>();

    protected final int centerX;
    protected final int centerY;

    protected EditBox recipeNameField;
    protected EditBox fileNameField;

    public RecipeGeneratorUI(AbstractContainerScreen<?> screen) {
        this.screen = screen;
        this.centerX = screen.width / 2;
        this.centerY = screen.height / 2;
    }

    public static Button createButton(String text, int x, int y, Runnable action) {
        return Button.builder(
            Component.literal(text),
            btn -> action.run()
        )
        .bounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT)
        .build();
    }

    protected <T extends Renderable & GuiEventListener> T addComponent(T component) {
        renderables.add(component);
        children.add(component);
        return component;
    }

    protected Button addButton(Button button) {
        buttons.add(button);
        renderables.add(button);
        children.add(button);
        return button;
    }

    protected void createFields() {
        recipeNameField = addComponent(new EditBox(
            Minecraft.getInstance().font,
            centerX - 200,
            centerY - 70,
            FIELD_WIDTH,
            FIELD_HEIGHT,
            Component.literal("")
        ));
        recipeNameField.setMaxLength(100);
        recipeNameField.setHint(Component.literal("Recipe name"));

        fileNameField = addComponent(new EditBox(
            Minecraft.getInstance().font,
            centerX - 200,
            centerY - 45,
            FIELD_WIDTH,
            FIELD_HEIGHT,
            Component.literal("")
        ));
        fileNameField.setMaxLength(100);
        fileNameField.setHint(Component.literal("File name"));
    }

    protected void createButtons() {
        addButton(createButton("Generate", centerX + 95, centerY - 70, this::generateButton));
        addButton(createButton("Save", centerX + 95, centerY - 45, this::saveButton));
        addButton(createButton("Reload", centerX + 95, centerY - 20, this::reload));
        addButton(createButton("Close", centerX + 95, centerY + 55, screen::onClose));
    }

    public void addToScreen() {
        ScreenAccessor accessor = (ScreenAccessor) screen;
        List<Renderable> screenRenderables = accessor.getRenderables();
        List<GuiEventListener> screenChildren = accessor.getChildren();

        screenRenderables.addAll(renderables);
        screenChildren.addAll(children);
    }

    public void removeFromScreen() {
        ScreenAccessor accessor = (ScreenAccessor) screen;
        List<Renderable> screenRenderables = accessor.getRenderables();
        List<GuiEventListener> screenChildren = accessor.getChildren();

        screenRenderables.removeAll(renderables);
        screenChildren.removeAll(children);

        renderables.clear();
        children.clear();
        buttons.clear();
    }

    public void updateVisibility(boolean bookVisible) {
        boolean shouldShow = !CommonClass.isUIHidden && !bookVisible;
        for (Renderable renderable : renderables) {
            if (renderable instanceof net.minecraft.client.gui.components.AbstractWidget widget) {
                widget.visible = shouldShow;
                widget.active = shouldShow;
            }
        }
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (recipeNameField != null && recipeNameField.isFocused()) {
            recipeNameField.keyPressed(keyCode, scanCode, modifiers);
            return true;
        }
        if (fileNameField != null && fileNameField.isFocused()) {
            fileNameField.keyPressed(keyCode, scanCode, modifiers);
            return true;
        }
        return false;
    }

    protected String getRecipeName() {
        String name = recipeNameField != null ? recipeNameField.getValue().trim() : "";
        if (name.isEmpty()) {
            name = NameGenerator.generateName();
            if (recipeNameField != null) {
                recipeNameField.setValue(name);
            }
        }
        return name;
    }

    protected String getFileName() {
        String name = fileNameField != null ? fileNameField.getValue().trim() : "";
        if (name.isEmpty()) {
            name = NameGenerator.generateName();
            if (fileNameField != null) {
                fileNameField.setValue(name);
            }
        }
        return name;
    }

    protected void saveScript(String script, String fileName) {
        GenerationMethod method = CommonClass.generationMethod;
        Services.FILE_SAVER.save(script, fileName + method.getExtension(), method.getFolder());
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendSystemMessage(
                Component.literal(Component.translatable("recipe_generator.message.save_script") + fileName + method.getExtension())
            );
        }
    }

    protected void sendMessage(Component message) {
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendSystemMessage(message);
        }
    }
    
    protected void sendMessage(String message) {
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(message));
        }
    }

    protected void reload() {
        if (Minecraft.getInstance().player == null) return;
        Minecraft.getInstance().player.connection.sendCommand("reload");
        sendMessage("§aReload command sent");
    }

    public abstract void init();
    protected abstract void generateButton();
    protected abstract void saveButton();
}
