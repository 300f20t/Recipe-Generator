package io.github.ftincdev.recipe_generator.client.gui.crafting_table;

import io.github.ftincdev.recipe_generator.api.RecipeParams;
import io.github.ftincdev.recipe_generator.api.SlotsData;
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
import net.minecraft.world.item.ItemStack;

import io.github.ftincdev.recipe_generator.CommonClass;

public class CraftingTableRecipeGeneratorUI extends RecipeGeneratorUI {
    private Checkbox shapelessCheckbox;
    private RecipeType selectedType = RecipeType.SHAPED;

    private final VirtualSlot[] inputSlots = new VirtualSlot[9];
    private final ResultVirtualSlot resultSlot = new ResultVirtualSlot(124, 35);
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

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int index = row * 3 + col;
                int x = baseX + col * slotSize;
                int y = baseY + row * slotSize;
                inputSlots[index] = new VirtualSlot(x, y);
            }
        }
    }

    public void setPositions(int leftPos, int topPos) {
        this.leftPos = leftPos;
        this.topPos = topPos;
    }

    public void renderVirtualSlots(GuiGraphics guiGraphics) {
        for (VirtualSlot slot : inputSlots) {
            slot.render(guiGraphics, leftPos, topPos);
        }
        resultSlot.render(guiGraphics, leftPos, topPos);
    }

    public boolean handleVirtualSlotClick(double mouseX, double mouseY) {
        for (VirtualSlot slot : inputSlots) {
            if (slot.handleClick(mouseX, mouseY, leftPos, topPos)) {
                return true;
            }
        }
        return resultSlot.handleClick(mouseX, mouseY, leftPos, topPos);
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

    private SlotsData getSlotsData() {
        return SlotsData.fromVirtualSlots(inputSlots, resultSlot);
    }

    @Override
    protected void generate() {
        if (Minecraft.getInstance().player == null) return;

        String recipeName = getRecipeName();

        ItemStack result = resultSlot.getItem();
        if (result.isEmpty()) {
            sendMessage(Component.translatable("recipe_generator.message.result_empty"));
            return;
        }

        SlotsData slots = getSlotsData();
        RecipeParams params = new RecipeParams().set("type", selectedType);

        String generated = new CraftingTableGenerator().generate(slots, recipeName, params);
        sendMessage("§aGenerated recipe: \n§f" + generated);
    }

    @Override
    protected void save() {
        if (Minecraft.getInstance().player == null) return;

        String fileName = getFileName();
        String recipeName = getRecipeName();

        ItemStack result = resultSlot.getItem();
        if (result.isEmpty()) {
            sendMessage(Component.translatable("recipe_generator.message.result_empty"));
            return;
        }

        SlotsData slots = getSlotsData();
        RecipeParams params = new RecipeParams().set("type", selectedType);

        String script = new CraftingTableGenerator().generate(slots, recipeName, params);
        saveScript(script, fileName);
    }
}
