package com.github.matt159.putin.inventory.slots.minecraft;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSaddle extends Slot {

    public SlotSaddle(IInventory horseInventory, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(horseInventory, p_i1824_2_, p_i1824_3_, p_i1824_4_);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return super.isItemValid(itemStack) && itemStack.getItem() == Items.saddle && !this.getHasStack();
    }
}
