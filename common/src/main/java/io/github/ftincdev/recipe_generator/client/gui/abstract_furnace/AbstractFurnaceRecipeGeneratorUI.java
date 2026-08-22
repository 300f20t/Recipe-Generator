package io.github.ftincdev.recipe_generator.client.gui.abstract_furnace;

import io.github.ftincdev.recipe_generator.api.RecipeParams;
import io.github.ftincdev.recipe_generator.api.SlotsData;
import io.github.ftincdev.recipe_generator.api.client.gui.RecipeGeneratorUI;
import io.github.ftincdev.recipe_generator.api.VirtualSlot;
import io.github.ftincdev.recipe_generator.api.ResultVirtualSlot;
import io.github.ftincdev.recipe_generator.generator.abstract_furnace.AbstractFurnaceGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.item.ItemStack;

import io.github.ftincdev.recipe_generator.CommonClass;

public class AbstractFurnaceRecipeGeneratorUI<T extends AbstractFurnaceMenu> extends RecipeGeneratorUI {
    private final AbstractFurnaceScreen<T> screen;
    private final String furnaceType;

    private EditBox cookingTimeField;
    private EditBox experienceField;

    private final VirtualSlot inputSlot = new VirtualSlot(56, 17);
    private final ResultVirtualSlot resultSlot = new ResultVirtualSlot(116, 35);

    private int leftPos;
    private int topPos;

    public AbstractFurnaceRecipeGeneratorUI(AbstractFurnaceScreen<T> screen, String furnaceType) {
        super(screen);
        this.screen = screen;
        this.furnaceType = furnaceType;
    }

    public void setPositions(int leftPos, int topPos) {
        this.leftPos = leftPos;
        this.topPos = topPos;
    }

    public void renderVirtualSlots(GuiGraphics guiGraphics) {
        inputSlot.render(guiGraphics, leftPos, topPos);
        resultSlot.render(guiGraphics, leftPos, topPos);
    }

    public boolean handleVirtualSlotClick(double mouseX, double mouseY) {
        if (inputSlot.handleClick(mouseX, mouseY, leftPos, topPos)) return true;
        if (resultSlot.handleClick(mouseX, mouseY, leftPos, topPos)) return true;
        return false;
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
        boolean shouldShow = !CommonClass.isUIHidden && !bookVisible;
        if (cookingTimeField != null) {
            cookingTimeField.visible = shouldShow;
            cookingTimeField.active = shouldShow;
            experienceField.visible = shouldShow;
            experienceField.active = shouldShow;
        }
    }

    public boolean furnaceKeyPressed(int keyCode, int scanCode, int modifiers) {
        if (cookingTimeField != null && cookingTimeField.isFocused()) {
            cookingTimeField.keyPressed(keyCode, scanCode, modifiers);
            return true;
        }
        if (experienceField != null && experienceField.isFocused()) {
            experienceField.keyPressed(keyCode, scanCode, modifiers);
            return true;
        }
        return false;
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

        ItemStack input = inputSlot.getItem();
        ItemStack result = resultSlot.getItem();

        if (input.isEmpty()) {
            sendMessage("§cPlace the item in the ingredient slot!");
            return;
        }

        if (result.isEmpty()) {
            sendMessage("§cPlace the item in the result slot!");
            return;
        }

        SlotsData slots = SlotsData.fromSlots(menu.slots, 3);

        int cookingTime = parseCookingTime(cookingTimeField.getValue().trim());
        float experience = parseExperience(experienceField.getValue().trim());

        RecipeParams params = new RecipeParams()
            .set("furnaceType", furnaceType)
            .set("cookingTime", cookingTime)
            .set("experience", experience);

        String generated = new AbstractFurnaceGenerator().generate(slots, recipeName, params);
        sendMessage("§aGenerated recipe: \n§f" + generated);
    }

    @Override
    protected void save() {
        if (Minecraft.getInstance().player == null) return;

        AbstractFurnaceMenu menu = screen.getMenu();
        String fileName = getFileName();
        String recipeName = getRecipeName();

        ItemStack input = inputSlot.getItem();
        ItemStack result = resultSlot.getItem();

        if (input.isEmpty()) {
            sendMessage("§cPlace the item in the ingredient slot!");
            return;
        }

        if (result.isEmpty()) {
            sendMessage("§cPlace the item in the result slot!");
            return;
        }

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

    public ItemStack getInputItem() {
        return inputSlot.getItem();
    }

    public ItemStack getResultItem() {
        return resultSlot.getItem();
    }

    public void clearSlots() {
        inputSlot.clear();
        resultSlot.clear();
    }
}
