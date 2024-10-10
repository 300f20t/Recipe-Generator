package net.mcreator.recipe_generator.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.recipe_generator.world.inventory.GenerationMethodSelectionGUIMenu;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class GenerationMethodSelectionGUIScreen extends AbstractContainerScreen<GenerationMethodSelectionGUIMenu> {
	private final static HashMap<String, Object> guistate = GenerationMethodSelectionGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_crafttweaker;
	Button button_kubejs_wip;
	Button button_datapack_wip;
	Button button_custommod_wip;

	public GenerationMethodSelectionGUIScreen(GenerationMethodSelectionGUIMenu container, Inventory inventory, Component text) {
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
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
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
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.generation_method_selection_gui.label_select_the_generation_method"), 6, 7, -3355393, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.generation_method_selection_gui.label_not_installed"), 96, 34, -65485, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.generation_method_selection_gui.label_not_installed1"), 96, 61, -65485, false);
	}

	@Override
	public void init() {
		super.init();
		button_crafttweaker = Button.builder(Component.translatable("gui.recipe_generator.generation_method_selection_gui.button_crafttweaker"), e -> {
		}).bounds(this.leftPos + 6, this.topPos + 25, 88, 20).build();
		guistate.put("button:button_crafttweaker", button_crafttweaker);
		this.addRenderableWidget(button_crafttweaker);
		button_kubejs_wip = Button.builder(Component.translatable("gui.recipe_generator.generation_method_selection_gui.button_kubejs_wip"), e -> {
		}).bounds(this.leftPos + 6, this.topPos + 52, 87, 20).build();
		guistate.put("button:button_kubejs_wip", button_kubejs_wip);
		this.addRenderableWidget(button_kubejs_wip);
		button_datapack_wip = Button.builder(Component.translatable("gui.recipe_generator.generation_method_selection_gui.button_datapack_wip"), e -> {
		}).bounds(this.leftPos + 6, this.topPos + 79, 98, 20).build();
		guistate.put("button:button_datapack_wip", button_datapack_wip);
		this.addRenderableWidget(button_datapack_wip);
		button_custommod_wip = Button.builder(Component.translatable("gui.recipe_generator.generation_method_selection_gui.button_custommod_wip"), e -> {
		}).bounds(this.leftPos + 6, this.topPos + 106, 103, 20).build();
		guistate.put("button:button_custommod_wip", button_custommod_wip);
		this.addRenderableWidget(button_custommod_wip);
	}
}
