package net.mcreator.recipe_generator.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.recipe_generator.world.inventory.AvaritaCraftingTableRGUIMenu;
import net.mcreator.recipe_generator.network.AvaritaCraftingTableRGUIButtonMessage;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class AvaritaCraftingTableRGUIScreen extends AbstractContainerScreen<AvaritaCraftingTableRGUIMenu> {
	private final static HashMap<String, Object> guistate = AvaritaCraftingTableRGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox recipe_name;
	EditBox file_name;
	Button button_generate;
	Button button_save;
	Button button_close;
	Button button_reload;

	public AvaritaCraftingTableRGUIScreen(AvaritaCraftingTableRGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 273;
		this.imageHeight = 268;
	}

	private static final ResourceLocation texture = new ResourceLocation("recipe_generator:textures/screens/avarita_crafting_table_rgui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		recipe_name.render(guiGraphics, mouseX, mouseY, partialTicks);
		file_name.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("recipe_generator:textures/screens/crafting_table.png"), this.leftPos + 199, this.topPos + 40, 0, 0, 24, 17, 24, 17);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (recipe_name.isFocused())
			return recipe_name.keyPressed(key, b, c);
		if (file_name.isFocused())
			return file_name.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		recipe_name.tick();
		file_name.tick();
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String recipe_nameValue = recipe_name.getValue();
		String file_nameValue = file_name.getValue();
		super.resize(minecraft, width, height);
		recipe_name.setValue(recipe_nameValue);
		file_name.setValue(file_nameValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.avarita_crafting_table_rgui.label_recipe_name"), -125, 49, -3355393, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.avarita_crafting_table_rgui.label_file_name"), -116, 85, -3355393, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.avarita_crafting_table_rgui.label_crafting"), 91, -86, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.avarita_crafting_table_rgui.label_crafttweaker_only"), 82, 283, -65485, false);
	}

	@Override
	public void init() {
		super.init();
		recipe_name = new EditBox(this.font, this.leftPos + -124, this.topPos + 59, 118, 18, Component.translatable("gui.recipe_generator.avarita_crafting_table_rgui.recipe_name")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.recipe_generator.avarita_crafting_table_rgui.recipe_name").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.recipe_generator.avarita_crafting_table_rgui.recipe_name").getString());
				else
					setSuggestion(null);
			}
		};
		recipe_name.setSuggestion(Component.translatable("gui.recipe_generator.avarita_crafting_table_rgui.recipe_name").getString());
		recipe_name.setMaxLength(32767);
		guistate.put("text:recipe_name", recipe_name);
		this.addWidget(this.recipe_name);
		file_name = new EditBox(this.font, this.leftPos + -124, this.topPos + 95, 118, 18, Component.translatable("gui.recipe_generator.avarita_crafting_table_rgui.file_name")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.recipe_generator.avarita_crafting_table_rgui.file_name").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.recipe_generator.avarita_crafting_table_rgui.file_name").getString());
				else
					setSuggestion(null);
			}
		};
		file_name.setSuggestion(Component.translatable("gui.recipe_generator.avarita_crafting_table_rgui.file_name").getString());
		file_name.setMaxLength(32767);
		guistate.put("text:file_name", file_name);
		this.addWidget(this.file_name);
		button_generate = Button.builder(Component.translatable("gui.recipe_generator.avarita_crafting_table_rgui.button_generate"), e -> {
			if (true) {
				RecipeGeneratorMod.PACKET_HANDLER.sendToServer(new AvaritaCraftingTableRGUIButtonMessage(0, x, y, z));
				AvaritaCraftingTableRGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 280, this.topPos + 22, 67, 20).build();
		guistate.put("button:button_generate", button_generate);
		this.addRenderableWidget(button_generate);
		button_save = Button.builder(Component.translatable("gui.recipe_generator.avarita_crafting_table_rgui.button_save"), e -> {
			if (true) {
				RecipeGeneratorMod.PACKET_HANDLER.sendToServer(new AvaritaCraftingTableRGUIButtonMessage(1, x, y, z));
				AvaritaCraftingTableRGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 280, this.topPos + 49, 46, 20).build();
		guistate.put("button:button_save", button_save);
		this.addRenderableWidget(button_save);
		button_close = Button.builder(Component.translatable("gui.recipe_generator.avarita_crafting_table_rgui.button_close"), e -> {
			if (true) {
				RecipeGeneratorMod.PACKET_HANDLER.sendToServer(new AvaritaCraftingTableRGUIButtonMessage(2, x, y, z));
				AvaritaCraftingTableRGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 280, this.topPos + 193, 51, 20).build();
		guistate.put("button:button_close", button_close);
		this.addRenderableWidget(button_close);
		button_reload = Button.builder(Component.translatable("gui.recipe_generator.avarita_crafting_table_rgui.button_reload"), e -> {
			if (true) {
				RecipeGeneratorMod.PACKET_HANDLER.sendToServer(new AvaritaCraftingTableRGUIButtonMessage(3, x, y, z));
				AvaritaCraftingTableRGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 280, this.topPos + 76, 56, 20).build();
		guistate.put("button:button_reload", button_reload);
		this.addRenderableWidget(button_reload);
	}
}
