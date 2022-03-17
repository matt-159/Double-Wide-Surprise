package com.github.thebrochacho.putin.inventory;

import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

//Easier to copy paste code than expose a static inner class
public class SlotIngredient extends Slot {
    private static final String __OBFID = "CL_00001738";
    final ContainerBrewingStand this$0;

    public SlotIngredient(ContainerBrewingStand p_i1803_1_, IInventory p_i1803_2_, int p_i1803_3_, int p_i1803_4_, int p_i1803_5_) {
        super(p_i1803_2_, p_i1803_3_, p_i1803_4_, p_i1803_5_);
        this.this$0 = p_i1803_1_;
    }

    public boolean isItemValid(ItemStack p_75214_1_) {
        return p_75214_1_ != null ? p_75214_1_.getItem().isPotionIngredient(p_75214_1_) : false;
    }

    public int getSlotStackLimit() {
        return 64;
    }
}
