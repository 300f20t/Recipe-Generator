package net.mcreator.justctgui.client.gui;

import net.minecraft.world.World;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.justctgui.world.inventory.CraftingTableRemovingCTGUIMenu;
import net.mcreator.justctgui.network.CraftingTableRemovingCTGUIButtonMessage;
import net.mcreator.justctgui.JustCtguiMod;

import java.util.HashMap;

import com.mojang.blaze3d.platform.GlStateManager;

public class CraftingTableRemovingCTGUIScreen extends ContainerScreen<CraftingTableRemovingCTGUIMenu> {
	private final static HashMap<String, Object> guistate = CraftingTableRemovingCTGUIMenu.guistate;
	private final World world;
	private final int x, y, z;
	private final PlayerEntity entity;
	TextFieldWidget file_name;
	Button button_generate;
	Button button_save;
	Button button_close;
	Button button_reload;

	public CraftingTableRemovingCTGUIScreen(CraftingTableRemovingCTGUIMenu container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("just_ctgui:textures/screens/crafting_table_removing_ctgui.png");

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		file_name.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int gx, int gy) {
		GlStateManager.color4f(1, 1, 1, 1);
		GlStateManager.enableBlend();
		GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		this.blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		GlStateManager.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		if (file_name.isFocused())
			return file_name.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
		file_name.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.font.drawString(I18n.format("gui.just_ctgui.crafting_table_removing_ctgui.label_file_name"), -129, -2, -3355393);
		this.font.drawString(I18n.format("gui.just_ctgui.crafting_table_removing_ctgui.label_crafting"), 42, 7, -12829636);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		file_name = new TextFieldWidget(this.font, this.guiLeft + -128, this.guiTop + 8, 118, 18, I18n.format("gui.just_ctgui.crafting_table_removing_ctgui.file_name")) {
			@Override
			public void writeText(String text) {
				super.writeText(text);
				if (getText().isEmpty())
					setSuggestion(I18n.format("gui.just_ctgui.crafting_table_removing_ctgui.file_name"));
				else
					setSuggestion(null);
			}

			@Override
			public void setCursorPosition(int pos) {
				super.setCursorPosition(pos);
				if (getText().isEmpty())
					setSuggestion(I18n.format("gui.just_ctgui.crafting_table_removing_ctgui.file_name"));
				else
					setSuggestion(null);
			}
		};
		file_name.setSuggestion(I18n.format("gui.just_ctgui.crafting_table_removing_ctgui.file_name"));
		file_name.setMaxStringLength(32767);
		guistate.put("text:file_name", file_name);
		this.children.add(this.file_name);
		button_generate = new Button(this.guiLeft + 186, this.guiTop + 7, 67, 20, I18n.format("gui.just_ctgui.crafting_table_removing_ctgui.button_generate"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingTableRemovingCTGUIButtonMessage(0, x, y, z));
				CraftingTableRemovingCTGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:button_generate", button_generate);
		this.addButton(button_generate);
		button_save = new Button(this.guiLeft + 186, this.guiTop + 34, 46, 20, I18n.format("gui.just_ctgui.crafting_table_removing_ctgui.button_save"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingTableRemovingCTGUIButtonMessage(1, x, y, z));
				CraftingTableRemovingCTGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		guistate.put("button:button_save", button_save);
		this.addButton(button_save);
		button_close = new Button(this.guiLeft + 186, this.guiTop + 142, 51, 20, I18n.format("gui.just_ctgui.crafting_table_removing_ctgui.button_close"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingTableRemovingCTGUIButtonMessage(2, x, y, z));
				CraftingTableRemovingCTGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		guistate.put("button:button_close", button_close);
		this.addButton(button_close);
		button_reload = new Button(this.guiLeft + 186, this.guiTop + 61, 56, 20, I18n.format("gui.just_ctgui.crafting_table_removing_ctgui.button_reload"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingTableRemovingCTGUIButtonMessage(3, x, y, z));
				CraftingTableRemovingCTGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		});
		guistate.put("button:button_reload", button_reload);
		this.addButton(button_reload);
	}
}
