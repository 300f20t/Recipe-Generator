package io.github.ftincdev.recipe_generator.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SlotsData {
    private List<ItemStack> slots = new ArrayList<>();
    private int maxSlots = 1;

    public void addItem(ItemStack item) {
        slots.add(item);
    }

    public void setMaxSlots(int count) {
        maxSlots = count;
    }

    public void clear() {
        slots.clear();
    }

    public ItemStack getItem(int index) {
        return index >= 0 && index < slots.size() ? slots.get(index) : ItemStack.EMPTY;
    }

    public int getMaxSlots() {
        return maxSlots;
    }

    public static SlotsData fromSlots(NonNullList<Slot> slots, int maxSlots) {
        SlotsData data = new SlotsData();
        data.setMaxSlots(maxSlots);

        for (int i = 0; i < maxSlots; i++) {
            if (i < slots.size()) {
                Slot slot = slots.get(i);
                ItemStack stack = slot.getItem();
                data.addItem(stack.copy());
            } else {
                data.addItem(ItemStack.EMPTY);
            }
        }

        return data;
    }

    public static SlotsData fromArray(ItemStack[] stacks) {
        SlotsData data = new SlotsData();
        data.setMaxSlots(stacks.length);

        for (ItemStack stack : stacks) {
            data.addItem(stack.copy());
        }

        return data;
    }

    public static SlotsData fromVirtualSlots(VirtualSlot[] inputSlots, VirtualSlot resultSlot) {
        SlotsData data = new SlotsData();

        for (VirtualSlot slot : inputSlots) {
            data.addItem(slot.getItem().copy());
        }
        data.setMaxSlots(inputSlots.length + 1);
        data.addItem(resultSlot.getItem().copy());

        return data;
    }
}
