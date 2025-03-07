
package net.mcreator.recipe_generator.world.inventory;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.Container;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.recipe_generator.network.FurnaceCTGUISlotMessage;
import net.mcreator.recipe_generator.network.FurnaceCTGUIButtonMessage;
import net.mcreator.recipe_generator.init.RecipeGeneratorModMenus;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import java.util.HashMap;

public class FurnaceCTGUIMenu extends AbstractContainerMenu {
	public final static HashMap<String, Object> guistate = new HashMap<>();
	public final Level world;
	public final Player entity;
	public int x, y, z;
	private BlockPos pos;
	private final Container inventory;
	private boolean bound = false;

	public FurnaceCTGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		this(id, inv, new SimpleContainer(2));
		if (extraData != null) {
			pos = extraData.readBlockPos();
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();
		}
	}

	public FurnaceCTGUIMenu(int id, Inventory inv, Container container) {
		super(RecipeGeneratorModMenus.FURNACE_CTGUI, id);
		this.entity = inv.player;
		this.world = inv.player.level();
		this.inventory = container;
		this.addSlot(new Slot(inventory, 0, 43, 35) {
			private final int slot = 0;

			@Override
			public void setChanged() {
				super.setChanged();
				slotChanged(0, 0, 0);
			}
		});
		this.addSlot(new Slot(inventory, 1, 115, 35) {
			private final int slot = 1;

			@Override
			public void setChanged() {
				super.setChanged();
				slotChanged(1, 0, 0);
			}
		});
		for (int si = 0; si < 3; ++si)
			for (int sj = 0; sj < 9; ++sj)
				this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 0 + 84 + si * 18));
		for (int si = 0; si < 9; ++si)
			this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 0 + 142));
	}

	@Override
	public boolean stillValid(Player player) {
		return this.inventory.stillValid(player);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < 2) {
				if (!this.moveItemStackTo(itemstack1, 2, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickCraft(itemstack1, itemstack);
			} else if (!this.moveItemStackTo(itemstack1, 0, 2, false)) {
				if (index < 2 + 27) {
					if (!this.moveItemStackTo(itemstack1, 2 + 27, this.slots.size(), true))
						return ItemStack.EMPTY;
				} else {
					if (!this.moveItemStackTo(itemstack1, 2, 2 + 27, false))
						return ItemStack.EMPTY;
				}
				return ItemStack.EMPTY;
			}
			if (itemstack1.isEmpty())
				slot.set(ItemStack.EMPTY);
			else
				slot.setChanged();
			if (itemstack1.getCount() == itemstack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(player, itemstack1);
		}
		return itemstack;
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
	}

	private void slotChanged(int slotid, int ctype, int meta) {
		if (this.world != null && this.world.isClientSide())
			ClientPlayNetworking.send(new ResourceLocation(RecipeGeneratorMod.MODID, "furnacectgui_slot_" + slotid), new FurnaceCTGUISlotMessage(slotid, x, y, z, ctype, meta));
	}

	public static void screenInit() {
		ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(RecipeGeneratorMod.MODID, "furnacectgui_slot_0"), FurnaceCTGUISlotMessage::apply);
		ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(RecipeGeneratorMod.MODID, "furnacectgui_slot_1"), FurnaceCTGUISlotMessage::apply);
		ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(RecipeGeneratorMod.MODID, "furnacectgui_button_0"), FurnaceCTGUIButtonMessage::apply);
		ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(RecipeGeneratorMod.MODID, "furnacectgui_button_1"), FurnaceCTGUIButtonMessage::apply);
		ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(RecipeGeneratorMod.MODID, "furnacectgui_button_2"), FurnaceCTGUIButtonMessage::apply);
		ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(RecipeGeneratorMod.MODID, "furnacectgui_button_3"), FurnaceCTGUIButtonMessage::apply);
	}
}
