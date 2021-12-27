package com.github.matt159.putin.inventory;

import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.container.SlotBauble;
import baubles.common.lib.PlayerHandler;
import com.github.matt159.putin.Config;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;
import org.apache.commons.lang3.tuple.Pair;
import tconstruct.armor.inventory.SlotAccessory;
import tconstruct.armor.player.ArmorExtended;
import tconstruct.armor.player.TPlayerStats;
import travellersgear.api.TravellersGearAPI;
import travellersgear.common.inventory.InventoryTG;
import travellersgear.common.inventory.SlotRestricted;
import travellersgear.common.inventory.SlotRestricted.SlotType;

public class ContainerPutin extends ContainerPlayer {
    //Offset so that itemslots don't get mapped to each other
    private static final int INVENTORY_OFFSET = 27;
    public static int BAUBLES_SLOT_START = -1;
    public static int TINKERS_SLOT_START = -1;
    public static int TG_SLOT_START = -1;
    public static int GC_SLOT_START = -1;

    private InventoryBaubles baubles;
    private ArmorExtended tinkers;
    private InventoryTG travellers;

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

        int xOffset = 80;

        if (Config.isBaublesLoaded) {
            if (BAUBLES_SLOT_START == -1) {
                BAUBLES_SLOT_START = this.inventorySlots.size();
            }

            baubles = PlayerHandler.getPlayerBaubles(p_i1819_3_);
            baubles.setEventHandler(this);
            if (!p_i1819_3_.worldObj.isRemote) {
                baubles.stackList = PlayerHandler.getPlayerBaubles(p_i1819_3_).stackList;
            }

            this.addSlotToContainer(new SlotBauble(baubles, BaubleType.AMULET,0, xOffset,8 + 0 * 18));
            this.addSlotToContainer(new SlotBauble(baubles, BaubleType.RING,1, xOffset,8 + 1 * 18));
            this.addSlotToContainer(new SlotBauble(baubles, BaubleType.RING,2, xOffset,8 + 2 * 18));
            this.addSlotToContainer(new SlotBauble(baubles, BaubleType.BELT,3, xOffset,8 + 3 * 18));

            //size of one inventory slot;
            xOffset += 18;
        }

        if (Config.isTinkersLoaded) {
            if (TINKERS_SLOT_START == -1) {
                TINKERS_SLOT_START = this.inventorySlots.size();
            }

            tinkers = TPlayerStats.get(p_i1819_3_).armor;

            this.addSlotToContainer(new SlotAccessory(tinkers, 0, xOffset, 8 + 0 * 18, 1));
            this.addSlotToContainer(new SlotAccessory(tinkers, 1, xOffset, 8 + 1 * 18, 1));
            this.addSlotToContainer(new SlotAccessory(tinkers, 2, xOffset, 8 + 2 * 18, 1));
            this.addSlotToContainer(new SlotAccessory(tinkers, 3, xOffset, 8 + 3 * 18, 1));
            xOffset += 18;

            this.addSlotToContainer(new SlotAccessory(tinkers, 4, xOffset, 8 + 0 * 18, 10));
            this.addSlotToContainer(new SlotAccessory(tinkers, 5, xOffset, 8 + 1 * 18, 10));
            this.addSlotToContainer(new SlotAccessory(tinkers, 6, xOffset, 8 + 2 * 18, 10));
            xOffset += 18;
        }

        if (Config.isTravellersGearLoaded) {
            if (TG_SLOT_START == -1) {
                TG_SLOT_START = this.inventorySlots.size();
            }

            travellers = new InventoryTG(this, p_i1819_3_);
            if(!p_i1819_3_.worldObj.isRemote) {
                travellers.stackList = TravellersGearAPI.getExtendedInventory(p_i1819_3_);
            }

            this.addSlotToContainer(new SlotRestricted(travellers, 0, xOffset, 8 + 0 * 18, p_i1819_3_, SlotType.TRAVEL_CLOAK));
            this.addSlotToContainer(new SlotRestricted(travellers, 1, xOffset, 8 + 1 * 18, p_i1819_3_, SlotType.TRAVEL_SHOULDER));
            this.addSlotToContainer(new SlotRestricted(travellers, 2, xOffset, 8 + 2 * 18, p_i1819_3_, SlotType.TRAVEL_VAMBRACE));
            this.addSlotToContainer(new SlotRestricted(travellers, 3, xOffset, 8 + 3 * 18, p_i1819_3_, SlotType.TRAVEL_TITLE));
            xOffset += 18;
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
