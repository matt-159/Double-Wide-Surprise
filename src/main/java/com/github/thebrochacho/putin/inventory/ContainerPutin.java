package com.github.thebrochacho.putin.inventory;

import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.container.SlotBauble;
import baubles.common.lib.PlayerHandler;
import com.github.thebrochacho.putin.Config;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerPutin extends ContainerPlayer {
    //Offset so that itemslots don't get mapped to each other
    private static final int INVENTORY_OFFSET = 27;
    private InventoryBaubles baubles;
    private IInventory tinkers;

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

        if (Config.isBaublesLoaded) {
            baubles = new InventoryBaubles(p_i1819_3_);
            baubles.setEventHandler(this);
            if (!p_i1819_3_.worldObj.isRemote) {
                baubles.stackList = PlayerHandler.getPlayerBaubles(p_i1819_3_).stackList;
            }

            this.addSlotToContainer(new SlotBauble(baubles, BaubleType.AMULET,0,80,8 + 0 * 18));
            this.addSlotToContainer(new SlotBauble(baubles, BaubleType.RING,1,80,8 + 1 * 18));
            this.addSlotToContainer(new SlotBauble(baubles, BaubleType.RING,2,80,8 + 2 * 18));
            this.addSlotToContainer(new SlotBauble(baubles, BaubleType.BELT,3,80,8 + 3 * 18));
        }

        if (Config.isTinkersLoaded) {

        }

        if (Config.isTravellersGearLoaded) {

        }

        if (Config.isGalacticraftLoaded) {

        }
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);

        if (!player.worldObj.isRemote) {
            if (Config.isBaublesLoaded) {
                PlayerHandler.setPlayerBaubles(player, baubles);
            }
        }
    }
}
