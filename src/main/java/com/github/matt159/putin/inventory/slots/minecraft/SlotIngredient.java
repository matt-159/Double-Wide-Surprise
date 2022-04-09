package com.github.matt159.putin.inventory.slots.minecraft;

import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

//Easier to copy paste code than expose a static inner class
public class SlotIngredient extends Slot {
    private static final String __OBFID = "CL_00001738";
    final ContainerBrewingStand this$0;

    public SlotIngredient(ContainerBrewingStand containerBrewingStand, IInventory inventoryBrewingStand, int slotIndex, int xDisplayPosition, int yDisplayPosition) {
        super(inventoryBrewingStand, slotIndex, xDisplayPosition, yDisplayPosition);
        this.this$0 = containerBrewingStand;
    }

    public boolean isItemValid(ItemStack itemStack) {
        return itemStack != null ? itemStack.getItem().isPotionIngredient(itemStack) : false;
    }

    public int getSlotStackLimit() {
        return 64;
    }
}
