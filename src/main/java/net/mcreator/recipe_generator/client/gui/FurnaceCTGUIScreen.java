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

import net.mcreator.recipe_generator.world.inventory.FurnaceCTGUIMenu;
import net.mcreator.recipe_generator.procedures.InvertedCheckKubeJSProcedure;
import net.mcreator.recipe_generator.network.FurnaceCTGUIButtonMessage;
import net.mcreator.recipe_generator.init.RecipeGeneratorModScreens;

import com.mojang.blaze3d.systems.RenderSystem;

public class FurnaceCTGUIScreen extends AbstractContainerScreen<FurnaceCTGUIMenu> implements RecipeGeneratorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	EditBox recipe_name;
	EditBox file_name;
	EditBox XP;
	EditBox time;
	Button button_generate;
	Button button_save;
	Button button_close;
	Button button_reload;

	public FurnaceCTGUIScreen(FurnaceCTGUIMenu container, Inventory inventory, Component text) {
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
			else if (name.equals("XP"))
				XP.setValue(stringState);
			else if (name.equals("time"))
				time.setValue(stringState);
		}
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("recipe_generator:textures/screens/furnace_ctgui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		recipe_name.render(guiGraphics, mouseX, mouseY, partialTicks);
		file_name.render(guiGraphics, mouseX, mouseY, partialTicks);
		XP.render(guiGraphics, mouseX, mouseY, partialTicks);
		time.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(ResourceLocation.parse("recipe_generator:textures/screens/crafting_table.png"), this.leftPos + 78, this.topPos + 34, 0, 0, 24, 17, 24, 17);
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
		if (XP.isFocused())
			return XP.keyPressed(key, b, c);
		if (time.isFocused())
			return time.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String recipe_nameValue = recipe_name.getValue();
		String file_nameValue = file_name.getValue();
		String XPValue = XP.getValue();
		String timeValue = time.getValue();
		super.resize(minecraft, width, height);
		recipe_name.setValue(recipe_nameValue);
		file_name.setValue(file_nameValue);
		XP.setValue(XPValue);
		time.setValue(timeValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.furnace_ctgui.label_recipe_name"), -129, -2, -3355393, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.furnace_ctgui.label_file_name"), -129, 34, -3355393, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.furnace_ctgui.label_xp"), -129, 88, -3355393, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.furnace_ctgui.label_time"), -129, 133, -3355393, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.furnace_ctgui.label_furnace"), 69, 7, -12829636, false);
		if (InvertedCheckKubeJSProcedure.execute())
			guiGraphics.drawString(this.font, Component.translatable("gui.recipe_generator.furnace_ctgui.label_kubejs_is_not_supported"), 24, 16, -65485, false);
	}

	@Override
	public void init() {
		super.init();
		recipe_name = new EditBox(this.font, this.leftPos + -128, this.topPos + 8, 118, 18, Component.translatable("gui.recipe_generator.furnace_ctgui.recipe_name"));
		recipe_name.setMaxLength(8192);
		recipe_name.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "recipe_name", content, false);
		});
		recipe_name.setHint(Component.translatable("gui.recipe_generator.furnace_ctgui.recipe_name"));
		this.addWidget(this.recipe_name);
		file_name = new EditBox(this.font, this.leftPos + -128, this.topPos + 44, 118, 18, Component.translatable("gui.recipe_generator.furnace_ctgui.file_name"));
		file_name.setMaxLength(8192);
		file_name.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "file_name", content, false);
		});
		file_name.setHint(Component.translatable("gui.recipe_generator.furnace_ctgui.file_name"));
		this.addWidget(this.file_name);
		XP = new EditBox(this.font, this.leftPos + -128, this.topPos + 98, 118, 18, Component.translatable("gui.recipe_generator.furnace_ctgui.XP"));
		XP.setMaxLength(8192);
		XP.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "XP", content, false);
		});
		XP.setHint(Component.translatable("gui.recipe_generator.furnace_ctgui.XP"));
		this.addWidget(this.XP);
		time = new EditBox(this.font, this.leftPos + -128, this.topPos + 143, 118, 18, Component.translatable("gui.recipe_generator.furnace_ctgui.time"));
		time.setMaxLength(8192);
		time.setResponder(content -> {
			if (!menuStateUpdateActive)
				menu.sendMenuStateUpdate(entity, 0, "time", content, false);
		});
		time.setHint(Component.translatable("gui.recipe_generator.furnace_ctgui.time"));
		this.addWidget(this.time);
		button_generate = Button.builder(Component.translatable("gui.recipe_generator.furnace_ctgui.button_generate"), e -> {
			int x = FurnaceCTGUIScreen.this.x;
			int y = FurnaceCTGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new FurnaceCTGUIButtonMessage(0, x, y, z));
				FurnaceCTGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 7, 67, 20).build();
		this.addRenderableWidget(button_generate);
		button_save = Button.builder(Component.translatable("gui.recipe_generator.furnace_ctgui.button_save"), e -> {
			int x = FurnaceCTGUIScreen.this.x;
			int y = FurnaceCTGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new FurnaceCTGUIButtonMessage(1, x, y, z));
				FurnaceCTGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 34, 46, 20).build();
		this.addRenderableWidget(button_save);
		button_close = Button.builder(Component.translatable("gui.recipe_generator.furnace_ctgui.button_close"), e -> {
			int x = FurnaceCTGUIScreen.this.x;
			int y = FurnaceCTGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new FurnaceCTGUIButtonMessage(2, x, y, z));
				FurnaceCTGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 142, 51, 20).build();
		this.addRenderableWidget(button_close);
		button_reload = Button.builder(Component.translatable("gui.recipe_generator.furnace_ctgui.button_reload"), e -> {
			int x = FurnaceCTGUIScreen.this.x;
			int y = FurnaceCTGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new FurnaceCTGUIButtonMessage(3, x, y, z));
				FurnaceCTGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 61, 56, 20).build();
		this.addRenderableWidget(button_reload);
	}
}