package io.github.ftincdev.recipe_generator.client.gui.crafting_table;

import io.github.ftincdev.recipe_generator.api.RecipeParams;
import io.github.ftincdev.recipe_generator.api.SlotsData;
import io.github.ftincdev.recipe_generator.api.client.gui.RecipeGeneratorUI;
import io.github.ftincdev.recipe_generator.generator.crafting_table.CraftingTableGenerator;
import io.github.ftincdev.recipe_generator.generator.crafting_table.CraftingTableGenerator.RecipeType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.CraftingMenu;

import io.github.ftincdev.recipe_generator.CommonClass;

public class CraftingTableRecipeGeneratorUI extends RecipeGeneratorUI {
    private Checkbox shapelessCheckbox;
    private RecipeType selectedType = RecipeType.SHAPED;

    public CraftingTableRecipeGeneratorUI(CraftingScreen screen) {
        super(screen);
    }

    @Override
    public void init() {
        createFields();
        createButtons();
        createCheckbox();
    }

    private void createCheckbox() {
        shapelessCheckbox = addComponent(Checkbox.builder(
            Component.literal("Is shapeless?"),
            Minecraft.getInstance().font
        )
        .pos(centerX - 200, centerY - 20)
        .selected(false)
        .onValueChange((checkbox, selected) -> {
            selectedType = selected ? RecipeType.SHAPELESS : RecipeType.SHAPED;
        })
        .build());
    }

    @Override
    public void updateVisibility(boolean bookVisible) {
        super.updateVisibility(bookVisible);
        boolean shouldShow = !CommonClass.isUIHidden && !bookVisible;
        if (shapelessCheckbox != null) {
            shapelessCheckbox.visible = shouldShow;
            shapelessCheckbox.active = shouldShow;
        }
    }

    @Override
    protected void generate() {
        if (Minecraft.getInstance().player == null) return;

        String recipeName = getRecipeName();
        CraftingMenu menu = ((CraftingScreen) Minecraft.getInstance().screen).getMenu();
        SlotsData slots = SlotsData.fromSlots(menu.slots, 10);
        RecipeParams params = new RecipeParams().set("type", selectedType);

        String result = new CraftingTableGenerator().generate(slots, recipeName, params);
        sendMessage("§aGenerated recipe: \n§f" + result);
    }

    @Override
    protected void save() {
        if (Minecraft.getInstance().player == null) return;

        CraftingMenu menu = ((CraftingScreen) Minecraft.getInstance().screen).getMenu();
        String fileName = getFileName();
        String recipeName = getRecipeName();
        SlotsData slots = SlotsData.fromSlots(menu.slots, 10);
        RecipeParams params = new RecipeParams().set("type", selectedType);

        String script = new CraftingTableGenerator().generate(slots, recipeName, params);
        saveScript(script, fileName);
    }
}
