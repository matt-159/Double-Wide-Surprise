package com.github.thebrochacho.dws.interfaces.dws;

import net.minecraft.item.ItemStack;

public interface IAddsTGSlots {
    public int getTGSlotStart();

    public ItemStack[] getTravellersAccessoriesItemStacks();

    void setTravellersGearAccessories(ItemStack[] playerTGAccessories);
}
