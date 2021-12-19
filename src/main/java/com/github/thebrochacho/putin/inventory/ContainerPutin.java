package com.github.thebrochacho.putin.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;

public class ContainerPutin extends ContainerPlayer {
    //Offset so that itemslots don't get mapped to each other
    private static final int INVENTORY_OFFSET = 27;

    public ContainerPutin(InventoryPlayer p_i1819_1_, boolean p_i1819_2_, EntityPlayer p_i1819_3_) {
        super(p_i1819_1_, p_i1819_2_, p_i1819_3_);

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 9; j < 18; ++j)
            {
                this.addSlotToContainer(new Slot(p_i1819_1_, j + (i + 1) * 9 + INVENTORY_OFFSET, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 9; i < 18; ++i)
        {
            this.addSlotToContainer(new Slot(p_i1819_1_, i + INVENTORY_OFFSET, 8 + i * 18, 142));
        }
    }
}
