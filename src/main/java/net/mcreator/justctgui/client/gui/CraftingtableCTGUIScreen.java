package net.mcreator.justctgui.client.gui;

import net.minecraft.world.World;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.justctgui.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.justctgui.procedures.GetCurrentAxisProcedure;
import net.mcreator.justctgui.procedures.ATTENTIONProcedure;
import net.mcreator.justctgui.network.CraftingtableCTGUIButtonMessage;
import net.mcreator.justctgui.JustCtguiMod;

import java.util.HashMap;

import com.mojang.blaze3d.platform.GlStateManager;

public class CraftingtableCTGUIScreen extends ContainerScreen<CraftingtableCTGUIMenu> {
	private final static HashMap<String, Object> guistate = CraftingtableCTGUIMenu.guistate;
	private final World world;
	private final int x, y, z;
	private final PlayerEntity entity;
	TextFieldWidget recipe_name;
	TextFieldWidget file_name;
	CheckboxButton Is_shapeless;
	CheckboxButton Is_mirrored;
	Button button_all;
	Button button_diagonal;
	Button button_horizontal;
	Button button_none;
	Button button_vertical;
	Button button_generate;
	Button button_save;
	Button button_close;
	Button button_reload;

	public CraftingtableCTGUIScreen(CraftingtableCTGUIMenu container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 176;
		this.ySize = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("just_ctgui:textures/screens/craftingtable_ctgui.png");

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		recipe_name.render(mouseX, mouseY, partialTicks);
		file_name.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
		if (ATTENTIONProcedure.execute())
			if (mouseX > guiLeft + 254 && mouseX < guiLeft + 278 && mouseY > guiTop + 5 && mouseY < guiTop + 29)
				this.renderTooltip(I18n.format("gui.just_ctgui.craftingtable_ctgui.tooltip_attention_this_configuration_wil"), mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int gx, int gy) {
		GlStateManager.color4f(1, 1, 1, 1);
		GlStateManager.enableBlend();
		GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		this.blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("just_ctgui:textures/screens/crafting_table.png"));
		this.blit(this.guiLeft + 90, this.guiTop + 34, 0, 0, 24, 17, 24, 17);

		if (ATTENTIONProcedure.execute()) {
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("just_ctgui:textures/screens/popup_hint.png"));
			this.blit(this.guiLeft + 258, this.guiTop + 9, 0, 0, 16, 16, 16, 16);
		}
		GlStateManager.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		if (recipe_name.isFocused())
			return recipe_name.keyPressed(key, b, c);
		if (file_name.isFocused())
			return file_name.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
		recipe_name.tick();
		file_name.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.font.drawString(I18n.format("gui.just_ctgui.craftingtable_ctgui.label_recipe_name"), -129, -2, -3355393);
		this.font.drawString(I18n.format("gui.just_ctgui.craftingtable_ctgui.label_file_name"), -129, 34, -3355393);
		this.font.drawString(I18n.format("gui.just_ctgui.craftingtable_ctgui.label_empty"), -124, -35, -3355393);
		this.font.drawString(I18n.format("gui.just_ctgui.craftingtable_ctgui.label_current_axis"), 0, -35, -3355393);
		this.font.drawString(

				GetCurrentAxisProcedure.execute(), 68, -35, -3355393);
		this.font.drawString(I18n.format("gui.just_ctgui.craftingtable_ctgui.label_crafting"), 24, 5, -12829636);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		recipe_name = new TextFieldWidget(this.font, this.guiLeft + -128, this.guiTop + 8, 118, 18, I18n.format("gui.just_ctgui.craftingtable_ctgui.recipe_name")) {
			@Override
			public void writeText(String text) {
				super.writeText(text);
				if (getText().isEmpty())
					setSuggestion(I18n.format("gui.just_ctgui.craftingtable_ctgui.recipe_name"));
				else
					setSuggestion(null);
			}

			@Override
			public void setCursorPosition(int pos) {
				super.setCursorPosition(pos);
				if (getText().isEmpty())
					setSuggestion(I18n.format("gui.just_ctgui.craftingtable_ctgui.recipe_name"));
				else
					setSuggestion(null);
			}
		};
		recipe_name.setSuggestion(I18n.format("gui.just_ctgui.craftingtable_ctgui.recipe_name"));
		recipe_name.setMaxStringLength(32767);
		guistate.put("text:recipe_name", recipe_name);
		this.children.add(this.recipe_name);
		file_name = new TextFieldWidget(this.font, this.guiLeft + -128, this.guiTop + 44, 118, 18, I18n.format("gui.just_ctgui.craftingtable_ctgui.file_name")) {
			@Override
			public void writeText(String text) {
				super.writeText(text);
				if (getText().isEmpty())
					setSuggestion(I18n.format("gui.just_ctgui.craftingtable_ctgui.file_name"));
				else
					setSuggestion(null);
			}

			@Override
			public void setCursorPosition(int pos) {
				super.setCursorPosition(pos);
				if (getText().isEmpty())
					setSuggestion(I18n.format("gui.just_ctgui.craftingtable_ctgui.file_name"));
				else
					setSuggestion(null);
			}
		};
		file_name.setSuggestion(I18n.format("gui.just_ctgui.craftingtable_ctgui.file_name"));
		file_name.setMaxStringLength(32767);
		guistate.put("text:file_name", file_name);
		this.children.add(this.file_name);
		button_all = new Button(this.guiLeft + -124, this.guiTop + -25, 40, 20, I18n.format("gui.just_ctgui.craftingtable_ctgui.button_all"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingtableCTGUIButtonMessage(0, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:button_all", button_all);
		this.addButton(button_all);
		button_diagonal = new Button(this.guiLeft + 39, this.guiTop + -25, 67, 20, I18n.format("gui.just_ctgui.craftingtable_ctgui.button_diagonal"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingtableCTGUIButtonMessage(1, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		guistate.put("button:button_diagonal", button_diagonal);
		this.addButton(button_diagonal);
		button_horizontal = new Button(this.guiLeft + -38, this.guiTop + -25, 77, 20, I18n.format("gui.just_ctgui.craftingtable_ctgui.button_horizontal"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingtableCTGUIButtonMessage(2, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		guistate.put("button:button_horizontal", button_horizontal);
		this.addButton(button_horizontal);
		button_none = new Button(this.guiLeft + -84, this.guiTop + -25, 46, 20, I18n.format("gui.just_ctgui.craftingtable_ctgui.button_none"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingtableCTGUIButtonMessage(3, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		});
		guistate.put("button:button_none", button_none);
		this.addButton(button_none);
		button_vertical = new Button(this.guiLeft + 106, this.guiTop + -25, 67, 20, I18n.format("gui.just_ctgui.craftingtable_ctgui.button_vertical"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingtableCTGUIButtonMessage(4, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		});
		guistate.put("button:button_vertical", button_vertical);
		this.addButton(button_vertical);
		button_generate = new Button(this.guiLeft + 186, this.guiTop + 7, 67, 20, I18n.format("gui.just_ctgui.craftingtable_ctgui.button_generate"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingtableCTGUIButtonMessage(5, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		});
		guistate.put("button:button_generate", button_generate);
		this.addButton(button_generate);
		button_save = new Button(this.guiLeft + 186, this.guiTop + 34, 46, 20, I18n.format("gui.just_ctgui.craftingtable_ctgui.button_save"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingtableCTGUIButtonMessage(6, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 6, x, y, z);
			}
		});
		guistate.put("button:button_save", button_save);
		this.addButton(button_save);
		button_close = new Button(this.guiLeft + 186, this.guiTop + 142, 51, 20, I18n.format("gui.just_ctgui.craftingtable_ctgui.button_close"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingtableCTGUIButtonMessage(7, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 7, x, y, z);
			}
		});
		guistate.put("button:button_close", button_close);
		this.addButton(button_close);
		button_reload = new Button(this.guiLeft + 186, this.guiTop + 61, 56, 20, I18n.format("gui.just_ctgui.craftingtable_ctgui.button_reload"), e -> {
			if (true) {
				JustCtguiMod.PACKET_HANDLER.sendToServer(new CraftingtableCTGUIButtonMessage(8, x, y, z));
				CraftingtableCTGUIButtonMessage.handleButtonAction(entity, 8, x, y, z);
			}
		});
		guistate.put("button:button_reload", button_reload);
		this.addButton(button_reload);
		Is_shapeless = new CheckboxButton(this.guiLeft + -120, this.guiTop + 97, 20, 20, I18n.format("gui.just_ctgui.craftingtable_ctgui.Is_shapeless"), false);
		guistate.put("checkbox:Is_shapeless", Is_shapeless);
		this.addButton(Is_shapeless);
		Is_mirrored = new CheckboxButton(this.guiLeft + -120, this.guiTop + 70, 20, 20, I18n.format("gui.just_ctgui.craftingtable_ctgui.Is_mirrored"), false);
		guistate.put("checkbox:Is_mirrored", Is_mirrored);
		this.addButton(Is_mirrored);
	}
}
