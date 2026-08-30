package io.github.ftincdev.recipe_generator.client.gui.abstract_furnace;

import io.github.ftincdev.recipe_generator.api.IVirtualSlot;
import io.github.ftincdev.recipe_generator.api.RecipeParams;
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
import io.github.ftincdev.recipe_generator.CommonClass;

public class AbstractFurnaceRecipeGeneratorUI<T extends AbstractFurnaceMenu> extends RecipeGeneratorUI {
    private final String furnaceType;

    private EditBox cookingTimeField;
    private EditBox experienceField;

    private final IVirtualSlot[] slots = new IVirtualSlot[2];

    private int leftPos;
    private int topPos;

    public AbstractFurnaceRecipeGeneratorUI(AbstractFurnaceScreen<T> screen, String furnaceType) {
        super(screen);
        this.furnaceType = furnaceType;
        initSlots();
    }

    private void initSlots() {
        slots[0] = new VirtualSlot(56, 17);
        slots[1] = new ResultVirtualSlot(116, 35);
    }

    public void setPositions(int leftPos, int topPos) {
        this.leftPos = leftPos;
        this.topPos = topPos;
    }

    public void renderVirtualSlots(GuiGraphics guiGraphics, double mouseX, double mouseY) {
        for (IVirtualSlot slot : slots) {
            boolean hovered = isHoveringSlot(slot, mouseX, mouseY);
            slot.render(guiGraphics, leftPos, topPos, hovered);
        }
    }

    public boolean handleVirtualSlotClick(double mouseX, double mouseY) {
        if (slots[0].handleClick(mouseX, mouseY, leftPos, topPos)) return true;
        if (slots[1].handleClick(mouseX, mouseY, leftPos, topPos)) return true;
        return false;
    }

    public boolean isHoveringSlot(IVirtualSlot slot, double mouseX, double mouseY) {
        int screenX = leftPos + slot.getX();
        int screenY = topPos + slot.getY();
        return mouseX >= screenX && mouseX <= screenX + 16 &&
               mouseY >= screenY && mouseY <= screenY + 16;
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

    private boolean validateRecipe() {
        if (Minecraft.getInstance().player == null) return false;

        if (slots[0].isEmpty()) {
            sendMessage(Component.translatable("recipe_generator.message.input_empty"));
            return false;
        }

        if (slots[1].isEmpty()) {
            sendMessage(Component.translatable("recipe_generator.message.result_empty"));
            return false;
        }

        return true;
    }

    private String generate() {
        String recipeName = getRecipeName();

        int cookingTime = parseCookingTime(cookingTimeField.getValue().trim());
        float experience = parseExperience(experienceField.getValue().trim());

        RecipeParams params = new RecipeParams()
            .set("furnaceType", furnaceType)
            .set("cookingTime", cookingTime)
            .set("experience", experience);

        return new AbstractFurnaceGenerator().generate(slots, recipeName, params);
    }

    @Override
    protected void generateButton() {
        if (!validateRecipe()) return;

        String generated = generate();

        sendMessage(Component.translatable("recipe_generator.message.save_script").getString() + generated);
    }

    @Override
    protected void saveButton() {
        if (!validateRecipe()) return;

        String generated = generate();
        String fileName = getFileName();

        saveScript(generated, fileName);
    }
}
