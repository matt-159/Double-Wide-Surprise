package com.github.matt159.dws.inventory.slots.minecraft;

import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotBeacon extends ContainerBeacon.BeaconSlot {
    private static final String __OBFID = "CL_00001736";

    public SlotBeacon(ContainerBeacon container, IInventory inventory, int slotIndex, int xDisplayPosition, int yDisplayPosition) {
        container.super(inventory, slotIndex, xDisplayPosition, yDisplayPosition);
    }

    public boolean isItemValid(ItemStack itemStack) {
        return itemStack != null && itemStack.getItem() != null && itemStack.getItem().isBeaconPayment(itemStack);
    }

    public int getSlotStackLimit() {
        return 1;
    }
}
