package net.mcreator.recipe_generator.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.recipe_generator.world.inventory.CampFireRemovingRGUIMenu;
import net.mcreator.recipe_generator.procedures.InvertedCheckKubeJSProcedure;
import net.mcreator.recipe_generator.network.CampFireRemovingRGUIButtonMessage;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class CampFireRemovingRGUIScreen extends AbstractContainerScreen<CampFireRemovingRGUIMenu> {
	private final static HashMap<String, Object> guistate = CampFireRemovingRGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox file_name;
	Button button_generate;
	Button button_save;
	Button button_close;
	Button button_reload;

	public CampFireRemovingRGUIScreen(CampFireRemovingRGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("recipe_generator:textures/screens/camp_fire_removing_rgui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		file_name.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(RenderType::guiTextured, texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (file_name.isFocused())
			return file_name.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String file_nameValue = file_name.getValue();
		super.resize(minecraft, width, height);
		file_name.setValue(file_nameValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.camp_fire_removing_rgui.label_file_name"), -129, -2, -3355393, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.camp_fire_removing_rgui.label_furnace"), 51, 7, -12829636, false);
		if (InvertedCheckKubeJSProcedure.execute())
			guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.camp_fire_removing_rgui.label_kubejs_is_not_supported"), 33, 16, -65485, false);
	}

	@Override
	public void init() {
		super.init();
		file_name = new EditBox(this.font, this.leftPos + -128, this.topPos + 8, 118, 18, Component.translatable("gui.recipe_generator.camp_fire_removing_rgui.file_name")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.recipe_generator.camp_fire_removing_rgui.file_name").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos, boolean flag) {
				super.moveCursorTo(pos, flag);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.recipe_generator.camp_fire_removing_rgui.file_name").getString());
				else
					setSuggestion(null);
			}
		};
		file_name.setMaxLength(32767);
		file_name.setSuggestion(Component.translatable("gui.recipe_generator.camp_fire_removing_rgui.file_name").getString());
		guistate.put("text:file_name", file_name);
		this.addWidget(this.file_name);
		button_generate = Button.builder(Component.translatable("gui.recipe_generator.camp_fire_removing_rgui.button_generate"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new CampFireRemovingRGUIButtonMessage(0, x, y, z));
				CampFireRemovingRGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 7, 67, 20).build();
		guistate.put("button:button_generate", button_generate);
		this.addRenderableWidget(button_generate);
		button_save = Button.builder(Component.translatable("gui.recipe_generator.camp_fire_removing_rgui.button_save"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new CampFireRemovingRGUIButtonMessage(1, x, y, z));
				CampFireRemovingRGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 34, 46, 20).build();
		guistate.put("button:button_save", button_save);
		this.addRenderableWidget(button_save);
		button_close = Button.builder(Component.translatable("gui.recipe_generator.camp_fire_removing_rgui.button_close"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new CampFireRemovingRGUIButtonMessage(2, x, y, z));
				CampFireRemovingRGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 142, 51, 20).build();
		guistate.put("button:button_close", button_close);
		this.addRenderableWidget(button_close);
		button_reload = Button.builder(Component.translatable("gui.recipe_generator.camp_fire_removing_rgui.button_reload"), e -> {
			if (true) {
				PacketDistributor.sendToServer(new CampFireRemovingRGUIButtonMessage(3, x, y, z));
				CampFireRemovingRGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 61, 56, 20).build();
		guistate.put("button:button_reload", button_reload);
		this.addRenderableWidget(button_reload);
	}
}
