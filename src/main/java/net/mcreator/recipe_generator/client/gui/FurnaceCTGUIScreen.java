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

import net.mcreator.recipe_generator.world.inventory.FurnaceCTGUIMenu;
import net.mcreator.recipe_generator.procedures.InvertedCheckKubeJSProcedure;
import net.mcreator.recipe_generator.network.FurnaceCTGUIButtonMessage;
import net.mcreator.recipe_generator.init.RecipeGeneratorModScreens.WidgetScreen;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class FurnaceCTGUIScreen extends AbstractContainerScreen<FurnaceCTGUIMenu> implements WidgetScreen {
	private final static HashMap<String, Object> guistate = FurnaceCTGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	public static EditBox recipe_name;
	public static EditBox file_name;
	public static EditBox XP;
	public static EditBox time;
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

	private static final ResourceLocation texture = new ResourceLocation("recipe_generator:textures/screens/furnace_ctgui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		recipe_name.render(guiGraphics, mouseX, mouseY, partialTicks);
		file_name.render(guiGraphics, mouseX, mouseY, partialTicks);
		XP.render(guiGraphics, mouseX, mouseY, partialTicks);
		time.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("recipe_generator:textures/screens/crafting_table.png"), this.leftPos + 78, this.topPos + 34, 0, 0, 24, 17, 24, 17);

		RenderSystem.disableBlend();
	}

	public static HashMap<String, String> getTextboxValues() {
		textstate.put("textin:recipe_name", recipe_name.getValue());
		textstate.put("textin:file_name", file_name.getValue());
		textstate.put("textin:XP", XP.getValue());
		textstate.put("textin:time", time.getValue());
		return textstate;
	}

	public HashMap<String, Object> getWidgets() {
		return guistate;
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
	public void containerTick() {
		super.containerTick();
		recipe_name.tick();
		file_name.tick();
		XP.tick();
		time.tick();
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
		recipe_name = new EditBox(this.font, this.leftPos + -128, this.topPos + 8, 118, 18, Component.translatable("gui.recipe_generator.furnace_ctgui.recipe_name")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.recipe_generator.furnace_ctgui.recipe_name").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.recipe_generator.furnace_ctgui.recipe_name").getString());
				else
					setSuggestion(null);
			}
		};
		recipe_name.setSuggestion(Component.translatable("gui.recipe_generator.furnace_ctgui.recipe_name").getString());
		recipe_name.setMaxLength(32767);
		guistate.put("text:recipe_name", recipe_name);
		this.addWidget(this.recipe_name);
		file_name = new EditBox(this.font, this.leftPos + -128, this.topPos + 44, 118, 18, Component.translatable("gui.recipe_generator.furnace_ctgui.file_name")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.recipe_generator.furnace_ctgui.file_name").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.recipe_generator.furnace_ctgui.file_name").getString());
				else
					setSuggestion(null);
			}
		};
		file_name.setSuggestion(Component.translatable("gui.recipe_generator.furnace_ctgui.file_name").getString());
		file_name.setMaxLength(32767);
		guistate.put("text:file_name", file_name);
		this.addWidget(this.file_name);
		XP = new EditBox(this.font, this.leftPos + -128, this.topPos + 98, 118, 18, Component.translatable("gui.recipe_generator.furnace_ctgui.XP")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.recipe_generator.furnace_ctgui.XP").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.recipe_generator.furnace_ctgui.XP").getString());
				else
					setSuggestion(null);
			}
		};
		XP.setSuggestion(Component.translatable("gui.recipe_generator.furnace_ctgui.XP").getString());
		XP.setMaxLength(32767);
		guistate.put("text:XP", XP);
		this.addWidget(this.XP);
		time = new EditBox(this.font, this.leftPos + -128, this.topPos + 143, 118, 18, Component.translatable("gui.recipe_generator.furnace_ctgui.time")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.recipe_generator.furnace_ctgui.time").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.recipe_generator.furnace_ctgui.time").getString());
				else
					setSuggestion(null);
			}
		};
		time.setSuggestion(Component.translatable("gui.recipe_generator.furnace_ctgui.time").getString());
		time.setMaxLength(32767);
		guistate.put("text:time", time);
		this.addWidget(this.time);
		button_generate = Button.builder(Component.translatable("gui.recipe_generator.furnace_ctgui.button_generate"), e -> {
			if (true) {
				textstate.put("textin:recipe_name", recipe_name.getValue());
				textstate.put("textin:file_name", file_name.getValue());
				textstate.put("textin:XP", XP.getValue());
				textstate.put("textin:time", time.getValue());
				RecipeGeneratorMod.PACKET_HANDLER.sendToServer(new FurnaceCTGUIButtonMessage(0, x, y, z, textstate));
				FurnaceCTGUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 186, this.topPos + 7, 67, 20).build();
		guistate.put("button:button_generate", button_generate);
		this.addRenderableWidget(button_generate);
		button_save = Button.builder(Component.translatable("gui.recipe_generator.furnace_ctgui.button_save"), e -> {
			if (true) {
				textstate.put("textin:recipe_name", recipe_name.getValue());
				textstate.put("textin:file_name", file_name.getValue());
				textstate.put("textin:XP", XP.getValue());
				textstate.put("textin:time", time.getValue());
				RecipeGeneratorMod.PACKET_HANDLER.sendToServer(new FurnaceCTGUIButtonMessage(1, x, y, z, textstate));
				FurnaceCTGUIButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 186, this.topPos + 34, 46, 20).build();
		guistate.put("button:button_save", button_save);
		this.addRenderableWidget(button_save);
		button_close = Button.builder(Component.translatable("gui.recipe_generator.furnace_ctgui.button_close"), e -> {
			if (true) {
				textstate.put("textin:recipe_name", recipe_name.getValue());
				textstate.put("textin:file_name", file_name.getValue());
				textstate.put("textin:XP", XP.getValue());
				textstate.put("textin:time", time.getValue());
				RecipeGeneratorMod.PACKET_HANDLER.sendToServer(new FurnaceCTGUIButtonMessage(2, x, y, z, textstate));
				FurnaceCTGUIButtonMessage.handleButtonAction(entity, 2, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 186, this.topPos + 142, 51, 20).build();
		guistate.put("button:button_close", button_close);
		this.addRenderableWidget(button_close);
		button_reload = Button.builder(Component.translatable("gui.recipe_generator.furnace_ctgui.button_reload"), e -> {
			if (true) {
				textstate.put("textin:recipe_name", recipe_name.getValue());
				textstate.put("textin:file_name", file_name.getValue());
				textstate.put("textin:XP", XP.getValue());
				textstate.put("textin:time", time.getValue());
				RecipeGeneratorMod.PACKET_HANDLER.sendToServer(new FurnaceCTGUIButtonMessage(3, x, y, z, textstate));
				FurnaceCTGUIButtonMessage.handleButtonAction(entity, 3, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 186, this.topPos + 61, 56, 20).build();
		guistate.put("button:button_reload", button_reload);
		this.addRenderableWidget(button_reload);
	}
}
