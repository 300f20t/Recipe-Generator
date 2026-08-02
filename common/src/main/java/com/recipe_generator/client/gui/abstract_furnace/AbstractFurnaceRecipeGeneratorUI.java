package com.recipe_generator.client.gui.abstract_furnace;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractFurnaceMenu;

import com.recipe_generator.api.RecipeParams;
import com.recipe_generator.api.SlotsData;
import com.recipe_generator.api.client.gui.RecipeGeneratorUI;
import com.recipe_generator.generator.abstract_furnace.AbstractFurnaceGenerator;

public class AbstractFurnaceRecipeGeneratorUI<T extends AbstractFurnaceMenu> extends RecipeGeneratorUI {
    private final AbstractFurnaceScreen<T> screen;
    private final String furnaceType;

    private EditBox cookingTimeField;
    private EditBox experienceField;

    public AbstractFurnaceRecipeGeneratorUI(AbstractFurnaceScreen<T> screen, String furnaceType) {
        super(screen);
        this.screen = screen;
        this.furnaceType = furnaceType;
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
    public void updateVisibility(boolean bookVisible) {
        super.updateVisibility(bookVisible);
        boolean shouldShow = !com.recipe_generator.CommonClass.isUIHidden && !bookVisible;
        if (cookingTimeField != null) {
            cookingTimeField.visible = shouldShow;
            cookingTimeField.active = shouldShow;
            experienceField.visible = shouldShow;
            experienceField.active = shouldShow;
        }
    }

    private int parseCookingTime(String value) {
        if (value.isEmpty()) return 200;
        try {
            int time = Integer.parseInt(value);
            return Math.max(1, time);
        } catch (NumberFormatException e) {
            return 200;
        }
    }

    private float parseExperience(String value) {
        if (value.isEmpty()) return 0.35f;
        try {
            float exp = Float.parseFloat(value);
            return Math.max(0, exp);
        } catch (NumberFormatException e) {
            return 0.35f;
        }
    }

    @Override
    protected void generate() {
        if (Minecraft.getInstance().player == null) return;

        String recipeName = getRecipeName();
        AbstractFurnaceMenu menu = screen.getMenu();
        SlotsData slots = SlotsData.fromSlots(menu.slots, 3);

        int cookingTime = parseCookingTime(cookingTimeField.getValue().trim());
        float experience = parseExperience(experienceField.getValue().trim());

        RecipeParams params = new RecipeParams()
            .set("furnaceType", furnaceType)
            .set("cookingTime", cookingTime)
            .set("experience", experience);

        String result = new AbstractFurnaceGenerator().generate(slots, recipeName, params);
        sendMessage("§aGenerated recipe: \n§f" + result);
    }

    @Override
    protected void save() {
        if (Minecraft.getInstance().player == null) return;

        AbstractFurnaceMenu menu = screen.getMenu();
        String fileName = getFileName();
        String recipeName = getRecipeName();
        SlotsData slots = SlotsData.fromSlots(menu.slots, 3);

        int cookingTime = parseCookingTime(cookingTimeField.getValue().trim());
        float experience = parseExperience(experienceField.getValue().trim());

        RecipeParams params = new RecipeParams()
            .set("furnaceType", furnaceType)
            .set("cookingTime", cookingTime)
            .set("experience", experience);

        String script = new AbstractFurnaceGenerator().generate(slots, recipeName, params);
        saveScript(script, fileName);
    }
}
