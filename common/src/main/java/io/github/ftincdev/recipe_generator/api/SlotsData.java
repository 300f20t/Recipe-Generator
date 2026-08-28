package io.github.ftincdev.recipe_generator.api;

import java.util.ArrayList;
import java.util.List;

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

    public static SlotsData fromVirtualSlots(VirtualSlot[] inputSlots, ResultVirtualSlot[] resultSlots) {
        SlotsData data = new SlotsData();
        data.setMaxSlots(inputSlots.length + resultSlots.length);

        for (int i = 0; i < resultSlots.length; i++) {
            if (i < resultSlots.length) {
                IVirtualSlot slot = resultSlots[i];
                ItemStack stack = slot.getItem();
                data.addItem(stack.copy());
            } else {
                data.addItem(ItemStack.EMPTY);
            }
        }

        for (int i = 0; i < inputSlots.length; i++) {
            if (i < inputSlots.length) {
                IVirtualSlot slot = inputSlots[i];
                ItemStack stack = slot.getItem();
                data.addItem(stack.copy());
            } else {
                data.addItem(ItemStack.EMPTY);
            }
        }

        return data;
    }

    public static SlotsData fromVirtualSlots(VirtualSlot[] inputSlots, ResultVirtualSlot resultSlot) {
        ResultVirtualSlot[] resultSlots = new ResultVirtualSlot[1];
        resultSlots[0] = resultSlot;
        return fromVirtualSlots(inputSlots, resultSlots);
    }

    public static SlotsData fromVirtualSlots(VirtualSlot inputSlot, ResultVirtualSlot[] resultSlots) {
        VirtualSlot[] inputSlots = new VirtualSlot[1];
        inputSlots[0] = inputSlot;
        return fromVirtualSlots(inputSlots, resultSlots);
    }

    public static SlotsData fromVirtualSlots(VirtualSlot inputSlot, ResultVirtualSlot resultSlot) {
        VirtualSlot[] inputSlots = new VirtualSlot[1];
        ResultVirtualSlot[] resultSlots = new ResultVirtualSlot[1];
        inputSlots[0] = inputSlot;
        resultSlots[0] = resultSlot;
        return fromVirtualSlots(inputSlots, resultSlots);
    }
}
