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

    public ItemStack getItem(int index) { return slots.get(index); }

    public int getMaxSlots() { return maxSlots; }

    public static SlotsData fromSlots(NonNullList<Slot> slots, int maxSlots) {
        SlotsData data = new SlotsData();
        data.setMaxSlots(maxSlots);
        
        for (int i = 0; i < maxSlots; i++) {
            Slot slot = slots.get(i);
            ItemStack stack = slot.getItem();
            data.addItem(stack);
        }

        return data;
    }
}
