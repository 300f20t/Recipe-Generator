package net.mcreator.recipe_generator.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

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

import net.mcreator.recipe_generator.world.inventory.SmithingRGUIMenu;
import net.mcreator.recipe_generator.procedures.InvertedCheckKubeJSProcedure;
import net.mcreator.recipe_generator.network.SmithingRGUIButtonMessage;
import net.mcreator.recipe_generator.init.RecipeGeneratorModScreens;

import com.mojang.blaze3d.systems.RenderSystem;

public class SmithingRGUIScreen extends AbstractContainerScreen<SmithingRGUIMenu> implements RecipeGeneratorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	EditBox recipe_name;
	EditBox file_name;
	Button button_generate;
	Button button_save;
	Button button_close;
	Button button_reload;

	public SmithingRGUIScreen(SmithingRGUIMenu container, Inventory inventory, Component text) {
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
			if (name.equals("recipe_name"))
				recipe_name.setValue(stringState);
			else if (name.equals("file_name"))
				file_name.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("recipe_generator:textures/screens/smithing_rgui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		recipe_name.render(guiGraphics, mouseX, mouseY, partialTicks);
		file_name.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(ResourceLocation.parse("recipe_generator:textures/screens/crafting_table.png"), this.leftPos + 96, this.topPos + 34, 0, 0, 24, 17, 24, 17);
		guiGraphics.blit(ResourceLocation.parse("recipe_generator:textures/screens/template_common.png"), this.leftPos + 24, this.topPos + 34, 0, 0, 16, 16, 16, 16);
		guiGraphics.blit(ResourceLocation.parse("recipe_generator:textures/screens/ingot_common.png"), this.leftPos + 60, this.topPos + 34, 0, 0, 16, 16, 16, 16);
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
	public void resize(Minecraft minecraft, int width, int height) {
		String recipe_nameValue = recipe_name.getValue();
		String file_nameValue = file_name.getValue();
		super.resize(minecraft, width, height);
		recipe_name.setValue(recipe_nameValue);
		file_name.setValue(file_nameValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.smithing_rgui.label_recipe_name"), -129, -2, -3355393, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.smithing_rgui.label_file_name"), -129, 34, -3355393, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.smithing_rgui.label_furnace"), 69, 7, -12829636, false);
		if (InvertedCheckKubeJSProcedure.execute())
			guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.smithing_rgui.label_kubejs_is_not_supported"), 24, 16, -65485, false);
	}

	@Override
	public void init() {
		super.init();
		recipe_name = new EditBox(this.font, this.leftPos + -128, this.topPos + 8, 118, 18, Component.translatable("gui.recipe_generator.smithing_rgui.recipe_name"));
		recipe_name.setMaxLength(8192);
		recipe_name.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "recipe_name", content, false);
		});
		recipe_name.setHint(Component.translatable("gui.recipe_generator.smithing_rgui.recipe_name"));
		this.addWidget(this.recipe_name);
		file_name = new EditBox(this.font, this.leftPos + -128, this.topPos + 44, 118, 18, Component.translatable("gui.recipe_generator.smithing_rgui.file_name"));
		file_name.setMaxLength(8192);
		file_name.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "file_name", content, false);
		});
		file_name.setHint(Component.translatable("gui.recipe_generator.smithing_rgui.file_name"));
		this.addWidget(this.file_name);
		button_generate = Button.builder(Component.translatable("gui.recipe_generator.smithing_rgui.button_generate"), e -> {
			int x = SmithingRGUIScreen.this.x;
			int y = SmithingRGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new SmithingRGUIButtonMessage(0, x, y, z));
				SmithingRGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 7, 67, 20).build();
		this.addRenderableWidget(button_generate);
		button_save = Button.builder(Component.translatable("gui.recipe_generator.smithing_rgui.button_save"), e -> {
			int x = SmithingRGUIScreen.this.x;
			int y = SmithingRGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new SmithingRGUIButtonMessage(1, x, y, z));
				SmithingRGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 34, 46, 20).build();
		this.addRenderableWidget(button_save);
		button_close = Button.builder(Component.translatable("gui.recipe_generator.smithing_rgui.button_close"), e -> {
			int x = SmithingRGUIScreen.this.x;
			int y = SmithingRGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new SmithingRGUIButtonMessage(2, x, y, z));
				SmithingRGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 142, 51, 20).build();
		this.addRenderableWidget(button_close);
		button_reload = Button.builder(Component.translatable("gui.recipe_generator.smithing_rgui.button_reload"), e -> {
			int x = SmithingRGUIScreen.this.x;
			int y = SmithingRGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new SmithingRGUIButtonMessage(3, x, y, z));
				SmithingRGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 61, 56, 20).build();
		this.addRenderableWidget(button_reload);
	}
}