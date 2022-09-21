package com.github.thebrochacho.dws.interfaces.minecraft;

import net.minecraft.inventory.IInventory;

public interface IContainerRepairMixin {
    IInventory getInputSlots();

    int getXPos();
    int getYPos();
    int getZPos();
}
