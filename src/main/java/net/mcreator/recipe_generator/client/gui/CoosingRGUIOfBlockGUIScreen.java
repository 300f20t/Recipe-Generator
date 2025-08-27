package net.mcreator.recipe_generator.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.recipe_generator.world.inventory.CoosingRGUIOfBlockGUIMenu;
import net.mcreator.recipe_generator.init.RecipeGeneratorModScreens;

import com.mojang.blaze3d.systems.RenderSystem;

public class CoosingRGUIOfBlockGUIScreen extends AbstractContainerScreen<CoosingRGUIOfBlockGUIMenu> implements RecipeGeneratorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	EditBox SearchRGUI;
	Button button_empty;

	public CoosingRGUIOfBlockGUIScreen(CoosingRGUIOfBlockGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		if (elementType == 0 && elementState instanceof String stringState) {
			if (name.equals("SearchRGUI"))
				SearchRGUI.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		SearchRGUI.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (SearchRGUI.isFocused())
			return SearchRGUI.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String SearchRGUIValue = SearchRGUI.getValue();
		super.resize(minecraft, width, height);
		SearchRGUI.setValue(SearchRGUIValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.coosing_rgui_of_block_gui.label_add_transform_reciepe"), 43, 20, -1, false);
	}

	@Override
	public void init() {
		super.init();
		SearchRGUI = new EditBox(this.font, this.leftPos + 29, this.topPos + -28, 118, 18, Component.translatable("gui.recipe_generator.coosing_rgui_of_block_gui.SearchRGUI"));
		SearchRGUI.setMaxLength(8192);
		SearchRGUI.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "SearchRGUI", content, false);
		});
		SearchRGUI.setHint(Component.translatable("gui.recipe_generator.coosing_rgui_of_block_gui.SearchRGUI"));
		this.addWidget(this.SearchRGUI);
		button_empty = Button.builder(Component.translatable("gui.recipe_generator.coosing_rgui_of_block_gui.button_empty"), e -> {
		}).bounds(this.leftPos + 42, this.topPos + 16, 95, 20).build();
		this.addRenderableWidget(button_empty);
	}
}