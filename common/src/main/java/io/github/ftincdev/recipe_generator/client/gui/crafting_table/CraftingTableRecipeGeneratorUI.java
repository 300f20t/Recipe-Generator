package io.github.ftincdev.recipe_generator.client.gui.crafting_table;

import io.github.ftincdev.recipe_generator.api.RecipeParams;
import io.github.ftincdev.recipe_generator.api.IVirtualSlot;
import io.github.ftincdev.recipe_generator.api.VirtualSlot;
import io.github.ftincdev.recipe_generator.api.ResultVirtualSlot;
import io.github.ftincdev.recipe_generator.api.client.gui.RecipeGeneratorUI;
import io.github.ftincdev.recipe_generator.generator.crafting_table.CraftingTableGenerator;
import io.github.ftincdev.recipe_generator.generator.crafting_table.CraftingTableGenerator.RecipeType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.network.chat.Component;

import io.github.ftincdev.recipe_generator.CommonClass;

public class CraftingTableRecipeGeneratorUI extends RecipeGeneratorUI {
    private Checkbox shapelessCheckbox;
    private RecipeType selectedType = RecipeType.SHAPED;

    private final IVirtualSlot[] slots = new IVirtualSlot[10];
    private int leftPos;
    private int topPos;

    public CraftingTableRecipeGeneratorUI(CraftingScreen screen) {
        super(screen);
        initSlots();
    }

    private void initSlots() {
        int baseX = 30;
        int baseY = 17;
        int slotSize = 18;

        slots[0] = new ResultVirtualSlot(124, 35);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int index = row * 3 + col;
                int x = baseX + col * slotSize;
                int y = baseY + row * slotSize;
                slots[index + 1] = new VirtualSlot(x, y);
            }
        }
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
        for (IVirtualSlot slot : slots) {
            if (slot.handleClick(mouseX, mouseY, leftPos, topPos)) {
                return true;
            }
        }
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

    private boolean validateRecipe() {
        if (Minecraft.getInstance().player == null) return false;

        if (slots[0].getItem().isEmpty()) {
            sendMessage(Component.translatable("recipe_generator.message.result_empty"));
            return false;
        }

        return true;
    }

    private String generate() {
        String recipeName = getRecipeName();

        RecipeParams params = new RecipeParams().set("type", selectedType);

        return new CraftingTableGenerator().generate(slots, recipeName, params);
    }

    @Override
    protected void generateButton() {
        if (!validateRecipe()) return;

        String generated = generate();

        sendMessage(Component.translatable("recipe_generator.message.generate_recipe").getString() + generated);
    }

    @Override
    protected void saveButton() {
        if (!validateRecipe()) return;

        String fileName = getFileName();
        String generated = generate();

        saveScript(generated, fileName);
    }
}
