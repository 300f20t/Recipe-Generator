package net.mcreator.recipe_generator.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.recipe_generator.world.inventory.CoosingRGUIOfBlockGUIMenu;
import net.mcreator.recipe_generator.procedures.TextLabelOfBlockRGUI4Procedure;
import net.mcreator.recipe_generator.procedures.TextLabelOfBlockRGUI3Procedure;
import net.mcreator.recipe_generator.procedures.TextLabelOfBlockRGUI2Procedure;
import net.mcreator.recipe_generator.procedures.TextLabelOfBlockRGUI1Procedure;
import net.mcreator.recipe_generator.init.RecipeGeneratorModScreens;

import com.mojang.blaze3d.systems.RenderSystem;

public class CoosingRGUIOfBlockGUIScreen extends AbstractContainerScreen<CoosingRGUIOfBlockGUIMenu> implements RecipeGeneratorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	EditBox SearchRGUI;
	Button button_empty4;
	Button button_empty5;
	ImageButton imagebutton_check_mark;
	ImageButton imagebutton_check_mark1;
	ImageButton imagebutton_check_mark2;
	ImageButton imagebutton_check_mark3;

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
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.coosing_rgui_of_block_gui.label_11"), 78, 151, -1, false);
		guiGraphics.drawString(this.font, TextLabelOfBlockRGUI1Procedure.execute(), 24, 7, -1, false);
		guiGraphics.drawString(this.font, TextLabelOfBlockRGUI2Procedure.execute(), 24, 43, -1, false);
		guiGraphics.drawString(this.font, TextLabelOfBlockRGUI3Procedure.execute(), 24, 79, -1, false);
		guiGraphics.drawString(this.font, TextLabelOfBlockRGUI4Procedure.execute(), 24, 115, -1, false);
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
		button_empty4 = Button.builder(Component.translatable("gui.recipe_generator.coosing_rgui_of_block_gui.button_empty4"), e -> {
		}).bounds(this.leftPos + 123, this.topPos + 151, 30, 20).build();
		this.addRenderableWidget(button_empty4);
		button_empty5 = Button.builder(Component.translatable("gui.recipe_generator.coosing_rgui_of_block_gui.button_empty5"), e -> {
		}).bounds(this.leftPos + 24, this.topPos + 151, 30, 20).build();
		this.addRenderableWidget(button_empty5);
		imagebutton_check_mark = new ImageButton(this.leftPos + 6, this.topPos + 7, 16, 16,
				new WidgetSprites(ResourceLocation.parse("recipe_generator:textures/screens/check_mark.png"), ResourceLocation.parse("recipe_generator:textures/screens/check_mark_active.png")), e -> {
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_check_mark);
		imagebutton_check_mark1 = new ImageButton(this.leftPos + 6, this.topPos + 43, 16, 16,
				new WidgetSprites(ResourceLocation.parse("recipe_generator:textures/screens/check_mark.png"), ResourceLocation.parse("recipe_generator:textures/screens/check_mark_active.png")), e -> {
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_check_mark1);
		imagebutton_check_mark2 = new ImageButton(this.leftPos + 6, this.topPos + 79, 16, 16,
				new WidgetSprites(ResourceLocation.parse("recipe_generator:textures/screens/check_mark.png"), ResourceLocation.parse("recipe_generator:textures/screens/check_mark_active.png")), e -> {
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_check_mark2);
		imagebutton_check_mark3 = new ImageButton(this.leftPos + 6, this.topPos + 115, 16, 16,
				new WidgetSprites(ResourceLocation.parse("recipe_generator:textures/screens/check_mark.png"), ResourceLocation.parse("recipe_generator:textures/screens/check_mark_active.png")), e -> {
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_check_mark3);
	}
}