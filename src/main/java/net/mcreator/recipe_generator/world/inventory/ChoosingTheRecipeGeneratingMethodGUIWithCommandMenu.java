
package net.mcreator.recipe_generator.world.inventory;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.Container;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.recipe_generator.network.ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage;
import net.mcreator.recipe_generator.init.RecipeGeneratorModMenus;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import java.util.HashMap;

public class ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu extends AbstractContainerMenu {
	public final static HashMap<String, Object> guistate = new HashMap<>();
	public final Level world;
	public final Player entity;
	public int x, y, z;
	private BlockPos pos;
	private final Container inventory;
	private boolean bound = false;

	public ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		this(id, inv, new SimpleContainer(0));
		if (extraData != null) {
			pos = extraData.readBlockPos();
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();
		}
	}

	public ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu(int id, Inventory inv, Container container) {
		super(RecipeGeneratorModMenus.CHOOSING_THE_RECIPE_GENERATING_METHOD_GUI_WITH_COMMAND, id);
		this.entity = inv.player;
		this.world = inv.player.level();
		this.inventory = container;
	}

	@Override
	public boolean stillValid(Player player) {
		return this.inventory.stillValid(player);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slot) {
		return ItemStack.EMPTY;
	}

	public static void screenInit() {
		ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(RecipeGeneratorMod.MODID, "choosingtherecipegeneratingmethodguiwithcommand_button_0"), ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage::apply);
		ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(RecipeGeneratorMod.MODID, "choosingtherecipegeneratingmethodguiwithcommand_button_1"), ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage::apply);
		ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(RecipeGeneratorMod.MODID, "choosingtherecipegeneratingmethodguiwithcommand_button_4"), ChoosingTheRecipeGeneratingMethodGUIWithCommandButtonMessage::apply);
	}
}
