package com.recipe_generator.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.CraftingMenu;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.recipe_generator.client.RecipeGeneratorClient;
import com.recipe_generator.client.RecipeGeneratorClient.GenerationMethod;
import com.recipe_generator.client.generator.crafting_table.Generator;
import com.recipe_generator.client.util.FileSaver;
import com.recipe_generator.client.util.NameGenerator;

public class RecipeGeneratorUI {
    private EditBox recipeNameField;
    private EditBox fileNameField;

    private final List<Button> buttons = new ArrayList<>();
    private final CraftingScreen screen;

    private final int centerX;
    private final int centerY;

    public RecipeGeneratorUI(CraftingScreen screen) {
        this.screen = screen;
        this.centerX = screen.width / 2;
        this.centerY = screen.height / 2;
    }

    public void init() {
        createFields();
        createButtons();
    }

    private void createFields() {
        recipeNameField = new EditBox(
            Minecraft.getInstance().font,
            centerX - 200,
            centerY - 70,
            100,
            20,
            Component.literal("")
        );
        recipeNameField.setMaxLength(100);
        recipeNameField.setHint(Component.literal("Recipe name"));

        fileNameField = new EditBox(
            Minecraft.getInstance().font,
            centerX - 200,
            centerY - 45,
            100,
            20,
            Component.literal("")
        );
        fileNameField.setMaxLength(100);
        fileNameField.setHint(Component.literal("File name"));
    }

    private void createButtons() {
        buttons.add(createButton("Generate", centerX + 95, centerY - 70, this::generate));
        buttons.add(createButton("Save", centerX + 95, centerY - 45, this::save));
        buttons.add(createButton("Reload", centerX + 95, centerY - 20, this::reload));
        buttons.add(createButton("Close", centerX + 95, centerY + 55, screen::onClose));
    }

    private Button createButton(String text, int x, int y, Runnable action) {
        return Button.builder(
            Component.literal(text),
            btn -> action.run()
        )
        .bounds(x, y, 60, 20)
        .build();
    }

    public void addToScreen() {
        try {
            Field renderablesField = Screen.class.getDeclaredField("renderables");
            renderablesField.setAccessible(true);
            List<Renderable> renderables = (List<Renderable>) renderablesField.get(screen);
            
            Field childrenField = Screen.class.getDeclaredField("children");
            childrenField.setAccessible(true);
            List<GuiEventListener> children = (List<GuiEventListener>) childrenField.get(screen);
            
            renderables.add(recipeNameField);
            children.add(recipeNameField);
            renderables.add(fileNameField);
            children.add(fileNameField);
            
            for (Button button : buttons) {
                renderables.add(button);
                children.add(button);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateVisibility(boolean bookVisible) {
        boolean shouldShow = !RecipeGeneratorClient.isUIHidden && !bookVisible;
        if (recipeNameField != null) {
            recipeNameField.visible = shouldShow;
            recipeNameField.active = shouldShow;
            fileNameField.visible = shouldShow;
            fileNameField.active = shouldShow;
            for (Button btn : buttons) {
                btn.visible = shouldShow;
                btn.active = shouldShow;
            }
        }
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (recipeNameField != null && recipeNameField.isFocused()) {
            if (recipeNameField.keyPressed(keyCode, scanCode, modifiers)) {
                return true;
            }
            return true;
        }
        if (fileNameField != null && fileNameField.isFocused()) {
            if (fileNameField.keyPressed(keyCode, scanCode, modifiers)) {
                return true;
            }
            return true;
        }
        return false;
    }

    public boolean charTyped(char codePoint, int modifiers) {
        if (recipeNameField != null && recipeNameField.isFocused()) {
            if (recipeNameField.charTyped(codePoint, modifiers)) {
                return true;
            }
            return true;
        }
        if (fileNameField != null && fileNameField.isFocused()) {
            if (fileNameField.charTyped(codePoint, modifiers)) {
                return true;
            }
            return true;
        }
        return false;
    }

    private void generate() {
        if (Minecraft.getInstance().player == null) return;

        String name = recipeNameField != null ? recipeNameField.getValue().trim() : "";
        if (name.isEmpty()) {
            name = new NameGenerator().generateName();
            if (recipeNameField != null) {
                recipeNameField.setValue(name);
            }
        }

        CraftingMenu menu = ((CraftingScreen) Minecraft.getInstance().screen).getMenu();
        Minecraft.getInstance().player.sendSystemMessage(
            Component.literal("§aGenerated recipe: \n§f" + new Generator().generate(menu.slots, name))
        );
    }

    private void save() {
        if (Minecraft.getInstance().player == null) return;

        CraftingMenu menu = ((CraftingScreen) Minecraft.getInstance().screen).getMenu();
        String name = fileNameField != null ? fileNameField.getValue().trim() : "";

        if (name.isEmpty()) {
            name = new NameGenerator().generateName();
            if (fileNameField != null) {
                fileNameField.setValue(name);
            }
        }

        String script = new Generator().generate(menu.slots, name);
        GenerationMethod method = RecipeGeneratorClient.generationMethod;
        FileSaver.save(script, name + method.getExtension(), method.getFolder());

        Minecraft.getInstance().player.sendSystemMessage(
            Component.literal("§aSaved as §f" + name + method.getExtension())
        );
    }

    private void reload() {
        if (Minecraft.getInstance().player == null) return;
        Minecraft.getInstance().player.connection.sendCommand("reload");
        Minecraft.getInstance().player.sendSystemMessage(
            Component.literal("§aReload command sent")
        );
    }
}
