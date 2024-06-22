package net.mcreator.justctgui.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.justctgui.world.inventory.CraftingTableRemovingRecipesGUIMenu;
import net.mcreator.justctgui.network.CraftingTableRemovingRecipesGUIButtonMessage;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class CraftingTableRemovingRecipesGUIScreen extends AbstractContainerScreen<CraftingTableRemovingRecipesGUIMenu> {
	private final static HashMap<String, Object> guistate = CraftingTableRemovingRecipesGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_generate;
	Button button_save;
	Button button_close;
	Button button_reload;

	public CraftingTableRemovingRecipesGUIScreen(CraftingTableRemovingRecipesGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("just_ctgui:textures/screens/crafting_table_removing_recipes_gui.png");

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
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("just_ctgui:textures/screens/crafting_table.png"), this.leftPos + 78, this.topPos + 34, 0, 0, 24, 17, 24, 17);

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
		guiGraphics.drawString(this.font, Component.translatable("gui.just_ctgui.crafting_table_removing_recipes_gui.label_crafting"), 69, 7, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_generate = Button.builder(Component.translatable("gui.just_ctgui.crafting_table_removing_recipes_gui.button_generate"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new CraftingTableRemovingRecipesGUIButtonMessage(0, x, y, z));
				CraftingTableRemovingRecipesGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 7, 67, 20).build();
		guistate.put("button:button_generate", button_generate);
		this.addRenderableWidget(button_generate);
		button_save = Button.builder(Component.translatable("gui.just_ctgui.crafting_table_removing_recipes_gui.button_save"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new CraftingTableRemovingRecipesGUIButtonMessage(1, x, y, z));
				CraftingTableRemovingRecipesGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 34, 46, 20).build();
		guistate.put("button:button_save", button_save);
		this.addRenderableWidget(button_save);
		button_close = Button.builder(Component.translatable("gui.just_ctgui.crafting_table_removing_recipes_gui.button_close"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new CraftingTableRemovingRecipesGUIButtonMessage(2, x, y, z));
				CraftingTableRemovingRecipesGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 142, 51, 20).build();
		guistate.put("button:button_close", button_close);
		this.addRenderableWidget(button_close);
		button_reload = Button.builder(Component.translatable("gui.just_ctgui.crafting_table_removing_recipes_gui.button_reload"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new CraftingTableRemovingRecipesGUIButtonMessage(3, x, y, z));
				CraftingTableRemovingRecipesGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 186, this.topPos + 61, 56, 20).build();
		guistate.put("button:button_reload", button_reload);
		this.addRenderableWidget(button_reload);
	}
}
