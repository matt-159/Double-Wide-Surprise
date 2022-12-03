package com.github.matt159.dws.interfaces.dws;

import net.minecraft.inventory.IInventory;

public interface IAddsBaubleSlots {

    IInventory getBaublesAccessories();

    void setBaublesAccessories(IInventory baublesAccessories);

    int getBaublesSlotStart();
}
