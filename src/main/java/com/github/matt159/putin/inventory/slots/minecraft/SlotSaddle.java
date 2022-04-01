package com.github.matt159.putin.inventory.slots.minecraft;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSaddle extends Slot {

    public SlotSaddle(IInventory horseInventory, int slotIndex, int xDisplayPosition, int yDisplayPosition) {
        super(horseInventory, slotIndex, xDisplayPosition, yDisplayPosition);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return super.isItemValid(itemStack) && itemStack.getItem() == Items.saddle && !this.getHasStack();
    }
}
