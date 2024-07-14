package net.mcreator.justctgui.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;

import net.mcreator.justctgui.world.inventory.CraftingTableRemovingCTGUIMenu;
import net.mcreator.justctgui.network.CraftingTableRemovingCTGUIButtonMessage;
import net.mcreator.justctgui.JustCtguiMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class CraftingTableRemovingCTGUIScreen extends AbstractContainerScreen<CraftingTableRemovingCTGUIMenu> {
	private final static HashMap<String, Object> guistate = CraftingTableRemovingCTGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox file_name;
	Button button_generate;
	Button button_save;
	Button button_close;
	Button button_reload;

	public CraftingTableRemovingCTGUIScreen(CraftingTableRemovingCTGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("just_ctgui:textures/screens/crafting_table_removing_ctgui.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		file_name.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
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
	public void containerTick() {
		super.containerTick();
		file_name.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, Component.translatable("gui.just_ctgui.crafting_table_removing_ctgui.label_file_name"), -129, -2, -3355393);
		this.font.draw(poseStack, Component.translatable("gui.just_ctgui.crafting_table_removing_ctgui.label_crafting"), 42, 7, -12829636);
	}

	@Override
	public void init() {
		super.init();
		file_name = new EditBox(this.font, this.leftPos + -128, this.topPos + 8, 118, 18, Component.translatable("gui.just_ctgui.crafting_table_removing_ctgui.file_name")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.just_ctgui.crafting_table_removing_ctgui.file_name").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.just_ctgui.crafting_table_removing_ctgui.file_name").getString());
				else
					setSuggestion(null);
			}
		};
		file_name.setSuggestion(Component.translatable("gui.just_ctgui.crafting_table_removing_ctgui.file_name").getString());
		file_name.setMaxLength(32767);
		guistate.put("text:file_name", file_name);
		this.addWidget(this.file_name);
		button_generate = new Button(this.leftPos + 186, this.topPos + 7, 67, 20, Component.translatable("gui.just_ctgui.crafting_table_removing_ctgui.button_generate"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingTableRemovingCTGUIButtonMessage(0, x, y, z));
				CraftingTableRemovingCTGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:button_generate", button_generate);
		this.addRenderableWidget(button_generate);
		button_save = new Button(this.leftPos + 186, this.topPos + 34, 46, 20, Component.translatable("gui.just_ctgui.crafting_table_removing_ctgui.button_save"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingTableRemovingCTGUIButtonMessage(1, x, y, z));
				CraftingTableRemovingCTGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		guistate.put("button:button_save", button_save);
		this.addRenderableWidget(button_save);
		button_close = new Button(this.leftPos + 186, this.topPos + 142, 51, 20, Component.translatable("gui.just_ctgui.crafting_table_removing_ctgui.button_close"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingTableRemovingCTGUIButtonMessage(2, x, y, z));
				CraftingTableRemovingCTGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		guistate.put("button:button_close", button_close);
		this.addRenderableWidget(button_close);
		button_reload = new Button(this.leftPos + 186, this.topPos + 61, 56, 20, Component.translatable("gui.just_ctgui.crafting_table_removing_ctgui.button_reload"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingTableRemovingCTGUIButtonMessage(3, x, y, z));
				CraftingTableRemovingCTGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		});
		guistate.put("button:button_reload", button_reload);
		this.addRenderableWidget(button_reload);
	}
}
