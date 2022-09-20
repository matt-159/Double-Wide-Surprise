package com.github.matt159.dws.interfaces.dws;

import net.minecraft.inventory.IInventory;

public interface IAddsBaubleSlots {

    public IInventory getBaublesAccessories();

    public void setBaublesAccessories(IInventory baublesAccessories);

    public int getBaublesSlotStart();
}
