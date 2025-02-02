package net.mcreator.recipe_generator.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;

import net.mcreator.recipe_generator.world.inventory.ChoosingTheRecipeGenerationMethodGUIMenu;
import net.mcreator.recipe_generator.procedures.ReturnSelectedGeneratingMethodProcedure;
import net.mcreator.recipe_generator.procedures.CheckKubeJSProcedure;
import net.mcreator.recipe_generator.procedures.CheckCraftTweakerProcedure;
import net.mcreator.recipe_generator.network.ChoosingTheRecipeGenerationMethodGUIButtonMessage;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class ChoosingTheRecipeGenerationMethodGUIScreen extends AbstractContainerScreen<ChoosingTheRecipeGenerationMethodGUIMenu> {
	private final static HashMap<String, Object> guistate = ChoosingTheRecipeGenerationMethodGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_crafttweaker;
	Button button_kubejs_wip;
	Button button_minecraft_mod_wip;
	Button button_minecraft_data_pack_wip;
	Button button_close;

	public ChoosingTheRecipeGenerationMethodGUIScreen(ChoosingTheRecipeGenerationMethodGUIMenu container, Inventory inventory, Component text) {
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
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
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
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, Component.translatable("gui.recipe_generator.choosing_the_recipe_generation_method_gui.label_selected_mod"), -48, 7, -6710785);
		this.font.draw(poseStack, Component.translatable("gui.recipe_generator.choosing_the_recipe_generation_method_gui.label_you_can_open_this_menu_with_the"), -57, -11, -154);
		this.font.draw(poseStack,

				ReturnSelectedGeneratingMethodProcedure.execute(world), 96, 7, -3355393);
		if (CheckCraftTweakerProcedure.execute())
			this.font.draw(poseStack, Component.translatable("gui.recipe_generator.choosing_the_recipe_generation_method_gui.label_not_installed"), 132, 34, -65536);
		if (CheckKubeJSProcedure.execute())
			this.font.draw(poseStack, Component.translatable("gui.recipe_generator.choosing_the_recipe_generation_method_gui.label_not_installed1"), 132, 61, -65536);
	}

	@Override
	public void init() {
		super.init();
		button_crafttweaker = new Button(this.leftPos + 42, this.topPos + 25, 88, 20, Component.translatable("gui.recipe_generator.choosing_the_recipe_generation_method_gui.button_crafttweaker"), e -> {
			if (true) {
				RecipeGeneratorMod.PACKET_HANDLER.sendToServer(new ChoosingTheRecipeGenerationMethodGUIButtonMessage(0, x, y, z));
				ChoosingTheRecipeGenerationMethodGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:button_crafttweaker", button_crafttweaker);
		this.addRenderableWidget(button_crafttweaker);
		button_kubejs_wip = new Button(this.leftPos + 42, this.topPos + 52, 87, 20, Component.translatable("gui.recipe_generator.choosing_the_recipe_generation_method_gui.button_kubejs_wip"), e -> {
			if (true) {
				RecipeGeneratorMod.PACKET_HANDLER.sendToServer(new ChoosingTheRecipeGenerationMethodGUIButtonMessage(1, x, y, z));
				ChoosingTheRecipeGenerationMethodGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		guistate.put("button:button_kubejs_wip", button_kubejs_wip);
		this.addRenderableWidget(button_kubejs_wip);
		button_minecraft_mod_wip = new Button(this.leftPos + 24, this.topPos + 106, 124, 20, Component.translatable("gui.recipe_generator.choosing_the_recipe_generation_method_gui.button_minecraft_mod_wip"), e -> {
		});
		guistate.put("button:button_minecraft_mod_wip", button_minecraft_mod_wip);
		this.addRenderableWidget(button_minecraft_mod_wip);
		button_minecraft_data_pack_wip = new Button(this.leftPos + 6, this.topPos + 79, 155, 20, Component.translatable("gui.recipe_generator.choosing_the_recipe_generation_method_gui.button_minecraft_data_pack_wip"), e -> {
		});
		guistate.put("button:button_minecraft_data_pack_wip", button_minecraft_data_pack_wip);
		this.addRenderableWidget(button_minecraft_data_pack_wip);
		button_close = new Button(this.leftPos + 60, this.topPos + 142, 51, 20, Component.translatable("gui.recipe_generator.choosing_the_recipe_generation_method_gui.button_close"), e -> {
			if (true) {
				RecipeGeneratorMod.PACKET_HANDLER.sendToServer(new ChoosingTheRecipeGenerationMethodGUIButtonMessage(4, x, y, z));
				ChoosingTheRecipeGenerationMethodGUIButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		});
		guistate.put("button:button_close", button_close);
		this.addRenderableWidget(button_close);
	}
}
