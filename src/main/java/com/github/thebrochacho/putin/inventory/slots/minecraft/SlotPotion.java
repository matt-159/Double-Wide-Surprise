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

    public SlotPotion(EntityPlayer entityPlayer, IInventory inventoryBrewingStand, int slotIndex, int xDisplayPosition, int yDisplayPosition) {
        super(inventoryBrewingStand, slotIndex, xDisplayPosition, yDisplayPosition);
        this.player = entityPlayer;
    }

    public boolean isItemValid(ItemStack itemStack) {
        return canHoldPotion(itemStack);
    }

    public int getSlotStackLimit() {
        return 1;
    }

    public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack) {
        if (itemStack.getItem() instanceof ItemPotion && itemStack.getItemDamage() > 0) {
            this.player.addStat(AchievementList.potion, 1);
        }

        super.onPickupFromSlot(entityPlayer, itemStack);
    }

    public static boolean canHoldPotion(ItemStack itemStack) {
        return itemStack != null && (itemStack.getItem() instanceof ItemPotion || itemStack.getItem() == Items.glass_bottle);
    }
}
