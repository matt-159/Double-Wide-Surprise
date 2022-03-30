package com.github.thebrochacho.putin.inventory.slots.minecraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;

//Easier to copy paste code than expose a static inner class
public class SlotPotion extends Slot {
    private EntityPlayer player;
    private static final String __OBFID = "CL_00001740";

    public SlotPotion(EntityPlayer p_i1804_1_, IInventory p_i1804_2_, int p_i1804_3_, int p_i1804_4_, int p_i1804_5_) {
        super(p_i1804_2_, p_i1804_3_, p_i1804_4_, p_i1804_5_);
        this.player = p_i1804_1_;
    }

    public boolean isItemValid(ItemStack p_75214_1_) {
        return canHoldPotion(p_75214_1_);
    }

    public int getSlotStackLimit() {
        return 1;
    }

    public void onPickupFromSlot(EntityPlayer p_82870_1_, ItemStack p_82870_2_) {
        if (p_82870_2_.getItem() instanceof ItemPotion && p_82870_2_.getItemDamage() > 0) {
            this.player.addStat(AchievementList.potion, 1);
        }

        super.onPickupFromSlot(p_82870_1_, p_82870_2_);
    }

    public static boolean canHoldPotion(ItemStack p_75243_0_) {
        return p_75243_0_ != null && (p_75243_0_.getItem() instanceof ItemPotion || p_75243_0_.getItem() == Items.glass_bottle);
    }
}
