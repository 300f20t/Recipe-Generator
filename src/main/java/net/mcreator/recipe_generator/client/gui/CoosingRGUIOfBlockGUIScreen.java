package net.mcreator.recipe_generator.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.recipe_generator.world.inventory.CoosingRGUIOfBlockGUIMenu;
import net.mcreator.recipe_generator.procedures.TextLabelOfBlockRGUI4Procedure;
import net.mcreator.recipe_generator.procedures.TextLabelOfBlockRGUI3Procedure;
import net.mcreator.recipe_generator.procedures.TextLabelOfBlockRGUI2Procedure;
import net.mcreator.recipe_generator.procedures.TextLabelOfBlockRGUI1Procedure;
import net.mcreator.recipe_generator.procedures.ButtonRGUIOfBlock4VisibleProcedure;
import net.mcreator.recipe_generator.procedures.ButtonRGUIOfBlock3VisibleProcedure;
import net.mcreator.recipe_generator.procedures.ButtonRGUIOfBlock2VisibleProcedure;
import net.mcreator.recipe_generator.procedures.ButtonRGUIOfBlock1VisibleProcedure;
import net.mcreator.recipe_generator.network.CoosingRGUIOfBlockGUIButtonMessage;
import net.mcreator.recipe_generator.init.RecipeGeneratorModScreens;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import com.mojang.blaze3d.systems.RenderSystem;

public class CoosingRGUIOfBlockGUIScreen extends AbstractContainerScreen<CoosingRGUIOfBlockGUIMenu> implements RecipeGeneratorModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
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
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
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
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, TextLabelOfBlockRGUI1Procedure.execute(), 24, 7, -1, false);
		guiGraphics.drawString(this.font, TextLabelOfBlockRGUI2Procedure.execute(), 24, 43, -1, false);
		guiGraphics.drawString(this.font, TextLabelOfBlockRGUI3Procedure.execute(), 24, 79, -1, false);
		guiGraphics.drawString(this.font, TextLabelOfBlockRGUI4Procedure.execute(), 24, 115, -1, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_check_mark = new ImageButton(this.leftPos + 6, this.topPos + 7, 16, 16, 0, 0, 16, ResourceLocation.parse("recipe_generator:textures/screens/atlas/imagebutton_check_mark.png"), 16, 32, e -> {
			int x = CoosingRGUIOfBlockGUIScreen.this.x;
			int y = CoosingRGUIOfBlockGUIScreen.this.y;
			if (ButtonRGUIOfBlock1VisibleProcedure.execute()) {
				RecipeGeneratorMod.PACKET_HANDLER.sendToServer(new CoosingRGUIOfBlockGUIButtonMessage(0, x, y, z));
				CoosingRGUIOfBlockGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_check_mark);
		imagebutton_check_mark1 = new ImageButton(this.leftPos + 6, this.topPos + 43, 16, 16, 0, 0, 16, ResourceLocation.parse("recipe_generator:textures/screens/atlas/imagebutton_check_mark1.png"), 16, 32, e -> {
			int x = CoosingRGUIOfBlockGUIScreen.this.x;
			int y = CoosingRGUIOfBlockGUIScreen.this.y;
			if (ButtonRGUIOfBlock2VisibleProcedure.execute()) {
				RecipeGeneratorMod.PACKET_HANDLER.sendToServer(new CoosingRGUIOfBlockGUIButtonMessage(1, x, y, z));
				CoosingRGUIOfBlockGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_check_mark1);
		imagebutton_check_mark2 = new ImageButton(this.leftPos + 6, this.topPos + 79, 16, 16, 0, 0, 16, ResourceLocation.parse("recipe_generator:textures/screens/atlas/imagebutton_check_mark2.png"), 16, 32, e -> {
			int x = CoosingRGUIOfBlockGUIScreen.this.x;
			int y = CoosingRGUIOfBlockGUIScreen.this.y;
			if (ButtonRGUIOfBlock3VisibleProcedure.execute()) {
				RecipeGeneratorMod.PACKET_HANDLER.sendToServer(new CoosingRGUIOfBlockGUIButtonMessage(2, x, y, z));
				CoosingRGUIOfBlockGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_check_mark2);
		imagebutton_check_mark3 = new ImageButton(this.leftPos + 6, this.topPos + 115, 16, 16, 0, 0, 16, ResourceLocation.parse("recipe_generator:textures/screens/atlas/imagebutton_check_mark3.png"), 16, 32, e -> {
			int x = CoosingRGUIOfBlockGUIScreen.this.x;
			int y = CoosingRGUIOfBlockGUIScreen.this.y;
			if (ButtonRGUIOfBlock4VisibleProcedure.execute()) {
				RecipeGeneratorMod.PACKET_HANDLER.sendToServer(new CoosingRGUIOfBlockGUIButtonMessage(3, x, y, z));
				CoosingRGUIOfBlockGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		});
		this.addRenderableWidget(imagebutton_check_mark3);
	}
}