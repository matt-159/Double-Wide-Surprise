package com.github.matt159.putin.inventory;

import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import com.github.matt159.putin.Config;
import com.github.matt159.putin.gui.SlotPutin;
import com.github.matt159.putin.gui.SlotPutin.SlotType;
import com.github.matt159.putin.util.ModCompat;
import micdoodle8.mods.galacticraft.core.inventory.InventoryExtended;
import micdoodle8.mods.galacticraft.core.inventory.SlotExtendedInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;
import tconstruct.armor.inventory.SlotAccessory;
import tconstruct.armor.player.ArmorExtended;
import tconstruct.armor.player.TPlayerStats;
import travellersgear.api.TravellersGearAPI;
import travellersgear.common.inventory.InventoryTG;
import travellersgear.common.inventory.SlotRestricted;

import java.util.ArrayList;

import static com.github.matt159.putin.gui.SlotPutin.SlotType.*;

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
    private InventoryExtended gc;
    public static ArrayList<Pair<Integer, Integer>> nullSlots = null;

    public ContainerPutin(InventoryPlayer inventoryPlayer, boolean p_i1819_2_, EntityPlayer player) {
        super(inventoryPlayer, p_i1819_2_, player);
        ArrayList<Pair<Integer, Integer>> nullSlots = new ArrayList<>();

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 9; j < 18; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + (i + 1) * 9 + INVENTORY_OFFSET, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 9; i < 18; ++i)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i + INVENTORY_OFFSET, 8 + i * 18, 142));
        }

        int xOffset = 80;

        if (Config.isBaublesLoaded) {
            if (BAUBLES_SLOT_START == -1) {
                BAUBLES_SLOT_START = this.inventorySlots.size();
            }

            baubles = PlayerHandler.getPlayerBaubles(player);
            baubles.setEventHandler(this);
            if (!player.worldObj.isRemote) {
                baubles.stackList = PlayerHandler.getPlayerBaubles(player).stackList;
            }

            this.addSlotToContainer(new SlotPutin(baubles, 0, xOffset,8 + 0 * 18, player, BAUBLE_AMULET));
            this.addSlotToContainer(new SlotPutin(baubles, 1, xOffset,8 + 1 * 18, player, BAUBLE_RING));
            this.addSlotToContainer(new SlotPutin(baubles, 2, xOffset,8 + 2 * 18, player, BAUBLE_RING));
            this.addSlotToContainer(new SlotPutin(baubles, 3, xOffset,8 + 3 * 18, player, BAUBLE_BELT));

            //size of one inventory slot;
            xOffset += 18;
        }

        if (Config.isTinkersLoaded) {
            if (TINKERS_SLOT_START == -1) {
                TINKERS_SLOT_START = this.inventorySlots.size();
            }

            tinkers = TPlayerStats.get(player).armor;

            //Mask
//            this.addSlotToContainer(new SlotAccessory(tinkers, 0, xOffset, 8 + 0 * 18, 1));
            this.addSlotToContainer(new SlotPutin(tinkers, 0, xOffset, 8 + 0 * 18, player, TINKERS_MASK));
            //Glove
//            this.addSlotToContainer(new SlotAccessory(tinkers, 1, xOffset, 8 + 1 * 18, 1));
            this.addSlotToContainer(new SlotPutin(tinkers, 1, xOffset, 8 + 1 * 18, player, TINKERS_GLOVE));
            //Belt
//            this.addSlotToContainer(new SlotAccessory(tinkers, 3, xOffset, 8 + 2 * 18, 1));
            this.addSlotToContainer(new SlotPutin(tinkers, 3, xOffset, 8 + 2 * 18, player, TINKERS_BELT));
            //Knapsack
//            this.addSlotToContainer(new SlotAccessory(tinkers, 2, xOffset, 8 + 3 * 18, 1));
            this.addSlotToContainer(new SlotPutin(tinkers, 2, xOffset, 8 + 3 * 18, player, TINKERS_KNAPSACK));
            xOffset += 18;

            //Red Canister
//            this.addSlotToContainer(new SlotAccessory(tinkers, 6, xOffset, 8 + 0 * 18, 10));
            this.addSlotToContainer(new SlotPutin(tinkers, 6, xOffset, 8 + 0 * 18, player, TINKERS_HEART_RED));
            //Yellow Canister
//            this.addSlotToContainer(new SlotAccessory(tinkers, 5, xOffset, 8 + 1 * 18, 10));
            this.addSlotToContainer(new SlotPutin(tinkers, 5, xOffset, 8 + 1 * 18, player, TINKERS_HEART_YELLOW));
            //Green Canister
//            this.addSlotToContainer(new SlotAccessory(tinkers, 4, xOffset, 8 + 2 * 18, 10));
            this.addSlotToContainer(new SlotPutin(tinkers, 4, xOffset, 8 + 2 * 18, player, TINKERS_HEART_GREEN));
            nullSlots.add(Pair.of(xOffset, 8 + 3 * 18));

            xOffset += 18;
        }

        if (Config.isTravellersGearLoaded) {
            if (TG_SLOT_START == -1) {
                TG_SLOT_START = this.inventorySlots.size();
            }

            travellers = new InventoryTG(this, player);
            if(!player.worldObj.isRemote) {
                travellers.stackList = TravellersGearAPI.getExtendedInventory(player);
            }

            this.addSlotToContainer(new SlotPutin(travellers, 0, xOffset, 8 + 0 * 18, player, TRAVEL_CLOAK));
            this.addSlotToContainer(new SlotPutin(travellers, 1, xOffset, 8 + 1 * 18, player, TRAVEL_PAULDRON));
            this.addSlotToContainer(new SlotPutin(travellers, 2, xOffset, 8 + 2 * 18, player, TRAVEL_VAMBRACE));
            this.addSlotToContainer(new SlotPutin(travellers, 3, xOffset, 8 + 3 * 18, player, TRAVEL_TITLE));
            xOffset += 18;
        }

        if (Config.isGalacticraftLoaded) {
            if (GC_SLOT_START == -1) {
                GC_SLOT_START = this.inventorySlots.size();
            }

            gc = ModCompat.getPlayerStats(player).extendedInventory;

            if (gc != null) {
                //Thermal Helmet
//                this.addSlotToContainer(new SlotExtendedInventory(gc, 6, xOffset, 8 + 0 * 18));
                this.addSlotToContainer(new SlotPutin(gc, 6, xOffset, 8 + 0 * 18, player, GC_THERMAL_HELM));
                //Thermal Chestplate
//                this.addSlotToContainer(new SlotExtendedInventory(gc, 7, xOffset, 8 + 1 * 18));
                this.addSlotToContainer(new SlotPutin(gc, 7, xOffset, 8 + 1 * 18, player, GC_THERMAL_CHEST));
                //Thermal Leggings
//                this.addSlotToContainer(new SlotExtendedInventory(gc, 8, xOffset, 8 + 2 * 18));
                this.addSlotToContainer(new SlotPutin(gc, 8, xOffset, 8 + 2 * 18, player, GC_THERMAL_LEGS));
                //Thermal Boots
//                this.addSlotToContainer(new SlotExtendedInventory(gc, 9, xOffset, 8 + 3 * 18));
                this.addSlotToContainer(new SlotPutin(gc, 9, xOffset, 8 + 3 * 18, player, GC_THERMAL_BOOTS));
                xOffset += 18;

                //Parachute
//                this.addSlotToContainer(new SlotExtendedInventory(gc, 4, xOffset, 8 + 0 * 18));
                this.addSlotToContainer(new SlotPutin(gc, 4, xOffset, 8 + 0 * 18, player, GC_PARACHUTE));
                //Oxygen Mask
//                this.addSlotToContainer(new SlotExtendedInventory(gc, 0, xOffset, 8 + 1 * 18));
                this.addSlotToContainer(new SlotPutin(gc, 0, xOffset, 8 + 1 * 18, player, GC_OXYGEN_MASK));
                //Oxygen Tank
//                this.addSlotToContainer(new SlotExtendedInventory(gc, 2, xOffset, 8 + 2 * 18));
                this.addSlotToContainer(new SlotPutin(gc, 2, xOffset, 8 + 2 * 18, player, GC_OXYGEN_TANK));
                nullSlots.add(Pair.of(xOffset, 8 + 3 * 18));
                xOffset += 18;

                //Frequency Module
//                this.addSlotToContainer(new SlotExtendedInventory(gc, 5, xOffset, 8 + 0 * 18));
                this.addSlotToContainer(new SlotPutin(gc, 5, xOffset, 8 + 0 * 18, player, GC_FREQUENCY_MODULE));
                //Oxygen gear
//                this.addSlotToContainer(new SlotExtendedInventory(gc, 1, xOffset, 8 + 1 * 18));
                this.addSlotToContainer(new SlotPutin(gc, 1, xOffset, 8 + 1 * 18, player, GC_OXYGEN_GEAR));
                //Oxygen Tank
//                this.addSlotToContainer(new SlotExtendedInventory(gc, 3, xOffset, 8 + 2 * 18));
                this.addSlotToContainer(new SlotPutin(gc, 3, xOffset, 8 + 2 * 18, player, GC_OXYGEN_TANK));
                nullSlots.add(Pair.of(xOffset, 8 + 3 * 18));
                xOffset += 18;
            }
        }

        if (ContainerPutin.nullSlots == null) {
            ContainerPutin.nullSlots = nullSlots;
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

    @Override
    public boolean canInteractWith(EntityPlayer var1)
    {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        return null;
    }
}
