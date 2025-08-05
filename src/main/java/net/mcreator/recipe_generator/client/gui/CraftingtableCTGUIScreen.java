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
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.recipe_generator.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.recipe_generator.procedures.GetCurrentAxisProcedure;
import net.mcreator.recipe_generator.network.CraftingtableCTGUIButtonMessage;
import net.mcreator.recipe_generator.init.RecipeGeneratorModScreens;

import com.mojang.blaze3d.systems.RenderSystem;

public class CraftingtableCTGUIScreen extends AbstractContainerScreen<CraftingtableCTGUIMenu> implements RecipeGeneratorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	EditBox recipe_name;
	EditBox file_name;
	Checkbox Is_shapeless;
	Checkbox Is_mirrored;
	Button button_all;
	Button button_diagonal;
	Button button_horizontal;
	Button button_none;
	Button button_vertical;
	Button button_generate;
	Button button_save;
	Button button_close;
	Button button_reload;

	public CraftingtableCTGUIScreen(CraftingtableCTGUIMenu container, Inventory inventory, Component text) {
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

	private static final ResourceLocation texture = ResourceLocation.parse("recipe_generator:textures/screens/craftingtable_ctgui.png");

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
		guiGraphics.blit(RenderType::guiTextured, texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(RenderType::guiTextured, ResourceLocation.parse("recipe_generator:textures/screens/crafting_table.png"), this.leftPos + 90, this.topPos + 34, 0, 0, 24, 17, 24, 17);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.craftingtable_ctgui.label_recipe_name"), -120, -2, -3355393, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.craftingtable_ctgui.label_file_name"), -120, 34, -3355393, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.craftingtable_ctgui.label_empty"), -124, -35, -3355393, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.craftingtable_ctgui.label_current_axis"), 0, -35, -3355393, false);
		guiGraphics.drawString(this.font, GetCurrentAxisProcedure.execute(), 68, -35, -3355393, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.craftingtable_ctgui.label_crafting"), 24, 5, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.craftingtable_ctgui.label_crafttweaker_only"), -120, 124, -256, false);
	}

	@Override
	public void init() {
		super.init();
		recipe_name = new EditBox(this.font, this.leftPos + -119, this.topPos + 8, 118, 18, Component.translatable("gui.recipe_generator.craftingtable_ctgui.recipe_name"));
		recipe_name.setMaxLength(8192);
		recipe_name.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "recipe_name", content, false);
		});
		recipe_name.setHint(Component.translatable("gui.recipe_generator.craftingtable_ctgui.recipe_name"));
		this.addWidget(this.recipe_name);
		file_name = new EditBox(this.font, this.leftPos + -119, this.topPos + 44, 118, 18, Component.translatable("gui.recipe_generator.craftingtable_ctgui.file_name"));
		file_name.setMaxLength(8192);
		file_name.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "file_name", content, false);
		});
		file_name.setHint(Component.translatable("gui.recipe_generator.craftingtable_ctgui.file_name"));
		this.addWidget(this.file_name);
		button_all = Button.builder(Component.translatable("gui.recipe_generator.craftingtable_ctgui.button_all"), e -> {
			int x = CraftingtableCTGUIScreen.this.x;
			int y = CraftingtableCTGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new CraftingtableCTGUIButtonMessage(0, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + -124, this.topPos + -25, 40, 20).build();
		this.addRenderableWidget(button_all);
		button_diagonal = Button.builder(Component.translatable("gui.recipe_generator.craftingtable_ctgui.button_diagonal"), e -> {
			int x = CraftingtableCTGUIScreen.this.x;
			int y = CraftingtableCTGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new CraftingtableCTGUIButtonMessage(1, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 39, this.topPos + -25, 67, 20).build();
		this.addRenderableWidget(button_diagonal);
		button_horizontal = Button.builder(Component.translatable("gui.recipe_generator.craftingtable_ctgui.button_horizontal"), e -> {
			int x = CraftingtableCTGUIScreen.this.x;
			int y = CraftingtableCTGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new CraftingtableCTGUIButtonMessage(2, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + -38, this.topPos + -25, 77, 20).build();
		this.addRenderableWidget(button_horizontal);
		button_none = Button.builder(Component.translatable("gui.recipe_generator.craftingtable_ctgui.button_none"), e -> {
			int x = CraftingtableCTGUIScreen.this.x;
			int y = CraftingtableCTGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new CraftingtableCTGUIButtonMessage(3, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + -84, this.topPos + -25, 46, 20).build();
		this.addRenderableWidget(button_none);
		button_vertical = Button.builder(Component.translatable("gui.recipe_generator.craftingtable_ctgui.button_vertical"), e -> {
			int x = CraftingtableCTGUIScreen.this.x;
			int y = CraftingtableCTGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new CraftingtableCTGUIButtonMessage(4, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}).bounds(this.leftPos + 106, this.topPos + -25, 67, 20).build();
		this.addRenderableWidget(button_vertical);
		button_generate = Button.builder(Component.translatable("gui.recipe_generator.craftingtable_ctgui.button_generate"), e -> {
			int x = CraftingtableCTGUIScreen.this.x;
			int y = CraftingtableCTGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new CraftingtableCTGUIButtonMessage(5, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 7, 67, 20).build();
		this.addRenderableWidget(button_generate);
		button_save = Button.builder(Component.translatable("gui.recipe_generator.craftingtable_ctgui.button_save"), e -> {
			int x = CraftingtableCTGUIScreen.this.x;
			int y = CraftingtableCTGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new CraftingtableCTGUIButtonMessage(6, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 6, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 34, 46, 20).build();
		this.addRenderableWidget(button_save);
		button_close = Button.builder(Component.translatable("gui.recipe_generator.craftingtable_ctgui.button_close"), e -> {
			int x = CraftingtableCTGUIScreen.this.x;
			int y = CraftingtableCTGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new CraftingtableCTGUIButtonMessage(7, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 7, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 142, 51, 20).build();
		this.addRenderableWidget(button_close);
		button_reload = Button.builder(Component.translatable("gui.recipe_generator.craftingtable_ctgui.button_reload"), e -> {
			int x = CraftingtableCTGUIScreen.this.x;
			int y = CraftingtableCTGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new CraftingtableCTGUIButtonMessage(8, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 8, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 61, 56, 20).build();
		this.addRenderableWidget(button_reload);
		Is_shapeless = Checkbox.builder(Component.translatable("gui.recipe_generator.craftingtable_ctgui.Is_shapeless"), this.font).pos(this.leftPos + -120, this.topPos + 70).onValueChange((checkbox, value) -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 1, "Is_shapeless", value, false);
		}).build();
		this.addRenderableWidget(Is_shapeless);
		Is_mirrored = Checkbox.builder(Component.translatable("gui.recipe_generator.craftingtable_ctgui.Is_mirrored"), this.font).pos(this.leftPos + -120, this.topPos + 97).onValueChange((checkbox, value) -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 1, "Is_mirrored", value, false);
		}).build();
		this.addRenderableWidget(Is_mirrored);
	}
}