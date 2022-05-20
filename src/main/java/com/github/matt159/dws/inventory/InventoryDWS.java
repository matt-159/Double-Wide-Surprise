package com.github.matt159.dws.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.Arrays;

public class InventoryDWS extends InventoryPlayer {

    public InventoryDWS(EntityPlayer p_i1750_1_) {
        super(p_i1750_1_);
        this.mainInventory = new ItemStack[72];
    }

    /**
     * Writes the inventory out as a list of compound tags. This is where the slot indices are used (+100 for armor, +80
     * for crafting).
     */
    @Override
    public NBTTagList writeToNBT(NBTTagList p_70442_1_)
    {
        int i;
        NBTTagCompound nbttagcompound;

        for (i = 0; i < this.mainInventory.length; ++i)
        {
            if (this.mainInventory[i] != null)
            {
                nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                this.mainInventory[i].writeToNBT(nbttagcompound);
                p_70442_1_.appendTag(nbttagcompound);
            }
        }

        for (i = 0; i < this.armorInventory.length; ++i)
        {
            if (this.armorInventory[i] != null)
            {
                nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)(i + 100));
                this.armorInventory[i].writeToNBT(nbttagcompound);
                p_70442_1_.appendTag(nbttagcompound);
            }
        }

        return p_70442_1_;
    }

    /**
     * Reads from the given tag list and fills the slots in the inventory with the correct items.
     */
    @Override
    public void readFromNBT(NBTTagList p_70443_1_)
    {
        this.mainInventory = new ItemStack[72];
        this.armorInventory = new ItemStack[4];

        for (int i = 0; i < p_70443_1_.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = p_70443_1_.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot") & 255;
            ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);

            if (itemstack != null)
            {
                if (j >= 0 && j < this.mainInventory.length)
                {
                    this.mainInventory[j] = itemstack;
                }

                if (j >= 100 && j < this.armorInventory.length + 100)
                {
                    this.armorInventory[j - 100] = itemstack;
                }
            }
        }
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        boolean retval = false;

        if (slot >= 0 && slot < 72) {
            retval = true;
        } else if (slot < 76) {
            retval = stack.getItem().isValidArmor(stack, 75 - slot, null);
        }

        return retval;
    }

    public static final int[] HOTBAR_SLOTS = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 63, 64, 65, 66, 67, 68, 69, 70, 71 };

    @Override
    public void changeCurrentItem(int delta) {
        //delta is checked to be not equal to 0 before this method is called
        delta = delta > 0 ? 1 : -1;

        //grab the index of the value stored in currentItem
        int index = Arrays.binarySearch(HOTBAR_SLOTS, this.currentItem);

        if (index < 0) return;

        //scrolling up -> delta = -1
        int newIndex = (index - delta) % getHotbarSize();
        newIndex = newIndex < 0 ? newIndex + getHotbarSize() : newIndex;

        this.currentItem = HOTBAR_SLOTS[newIndex];
    }

    @Override
    public ItemStack getCurrentItem() {
        return ((this.currentItem < 9 && this.currentItem >= 0) ||
                (this.currentItem < 72 && this.currentItem >= 63)) ? this.mainInventory[this.currentItem] : null;
    }
}
