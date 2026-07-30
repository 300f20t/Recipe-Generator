package com.recipe_generator.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.CraftingMenu;

import java.util.ArrayList;
import java.util.List;

import com.recipe_generator.CommonClass;
import com.recipe_generator.Constants.GenerationMethod;
import com.recipe_generator.api.RecipeParams;
import com.recipe_generator.api.SlotsData;
import com.recipe_generator.generator.crafting_table.CraftingTableGenerator;
import com.recipe_generator.generator.crafting_table.CraftingTableGenerator.RecipeType;
import com.recipe_generator.platform.Services;
import com.recipe_generator.client.mixin.accessor.ScreenAccessor;

public class RecipeGeneratorUI {
    private EditBox recipeNameField;
    private EditBox fileNameField;

    private Checkbox shapelessCheckbox;

    private final List<Button> buttons = new ArrayList<>();
    private final CraftingScreen screen;

    private RecipeType selectedType = RecipeType.SHAPED;

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
        createCheckbox();
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

    private void createCheckbox() {
        shapelessCheckbox = Checkbox.builder(
            Component.literal("Is shapeless?"),
            Minecraft.getInstance().font
        )
        .pos(centerX - 200, centerY - 20)
        .selected(false)
        .onValueChange((checkbox, selected) -> {
            selectedType = selected ? RecipeType.SHAPELESS : RecipeType.SHAPED;
        })
        .build();
    }

    public void addToScreen() {
        ScreenAccessor accessor = (ScreenAccessor) screen;
        List<Renderable> renderables = accessor.getRenderables();
        List<GuiEventListener> children = accessor.getChildren();
        
        renderables.add(recipeNameField);
        children.add(recipeNameField);
        renderables.add(fileNameField);
        children.add(fileNameField);
        renderables.add(shapelessCheckbox);
        children.add(shapelessCheckbox);
        for (Button button : buttons) {
            renderables.add(button);
            children.add(button);
        }
    }

    public void updateVisibility(boolean bookVisible) {
        boolean shouldShow = !CommonClass.isUIHidden && !bookVisible;
        if (recipeNameField != null) {
            recipeNameField.visible = shouldShow;
            recipeNameField.active = shouldShow;
            fileNameField.visible = shouldShow;
            fileNameField.active = shouldShow;

            shapelessCheckbox.visible = shouldShow;
            shapelessCheckbox.active = shouldShow;

            for (Button btn : buttons) {
                btn.visible = shouldShow;
                btn.active = shouldShow;
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

    public boolean charTyped(char codePoint, int modifiers) {
        if (recipeNameField != null && recipeNameField.isFocused()) {
            recipeNameField.charTyped(codePoint, modifiers);
            return true;
        }
        if (fileNameField != null && fileNameField.isFocused()) {
            fileNameField.charTyped(codePoint, modifiers);
            return true;
        }
        return false;
    }

    private void generate() {
        if (Minecraft.getInstance().player == null) return;

        String recipeName = recipeNameField != null ? recipeNameField.getValue().trim() : "";
        if (recipeName.isEmpty()) {
            recipeName = Services.NAME_GENERATOR.generateName();
            if (recipeNameField != null) {
                recipeNameField.setValue(recipeName);
            }
        }

        CraftingMenu menu = ((CraftingScreen) Minecraft.getInstance().screen).getMenu();

        SlotsData slots = SlotsData.fromSlots(menu.slots, 10);

        RecipeParams params = new RecipeParams().set("type", selectedType);

        Minecraft.getInstance().player.sendSystemMessage(
            Component.literal("§aGenerated recipe: \n§f" + new CraftingTableGenerator().generate(slots, recipeName, params))
        );
    }

    private void save() {
        if (Minecraft.getInstance().player == null) return;

        CraftingMenu menu = ((CraftingScreen) Minecraft.getInstance().screen).getMenu();
        String fileName = fileNameField != null ? fileNameField.getValue().trim() : "";

        if (fileName.isEmpty()) {
            fileName = Services.NAME_GENERATOR.generateName();
            if (fileNameField != null) {
                fileNameField.setValue(fileName);
            }
        }

        String recipeName = recipeNameField != null ? recipeNameField.getValue().trim() : "";
        
        if (recipeName.isEmpty()) {
            recipeName = Services.NAME_GENERATOR.generateName();
            if (recipeNameField != null) {
                recipeNameField.setValue(recipeName);
            }
        }

        SlotsData slots = SlotsData.fromSlots(menu.slots, 10);

        RecipeParams params = new RecipeParams().set("type", selectedType);

        String script = new CraftingTableGenerator().generate(slots, recipeName, params);
        GenerationMethod method = CommonClass.generationMethod;
        Services.FILE_SAVER.save(script, fileName + method.getExtension(), method.getFolder());

        Minecraft.getInstance().player.sendSystemMessage(
            Component.literal("§aSaved as §f" + fileName + method.getExtension())
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
