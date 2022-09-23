package com.github.thebrochacho.dws.interfaces.dws;

import net.minecraft.item.ItemStack;

public interface IAddsTGSlots {
    int getTGSlotStart();

    ItemStack[] getTravellersAccessoriesItemStacks();

    void setTravellersGearAccessories(ItemStack[] playerTGAccessories);
}
