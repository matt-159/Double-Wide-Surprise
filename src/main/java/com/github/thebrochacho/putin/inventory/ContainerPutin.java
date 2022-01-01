package com.github.thebrochacho.putin.inventory;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import com.github.thebrochacho.putin.Config;
import com.github.thebrochacho.putin.gui.SlotPutin;
import com.github.thebrochacho.putin.util.IGalacticWearable;
import com.github.thebrochacho.putin.util.ModCompat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.item.IItemThermal;
import micdoodle8.mods.galacticraft.core.inventory.InventoryExtended;
import micdoodle8.mods.galacticraft.core.items.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import org.apache.commons.lang3.tuple.Pair;
import tconstruct.armor.player.ArmorExtended;
import tconstruct.armor.player.TPlayerStats;
import tconstruct.library.accessory.IAccessory;
import travellersgear.api.ITravellersGear;
import travellersgear.api.TravellersGearAPI;
import travellersgear.common.inventory.InventoryTG;

import java.util.ArrayList;

import static com.github.thebrochacho.putin.gui.SlotPutin.SlotType.*;

public class ContainerPutin extends ContainerPlayer {
    //Offset so that itemslots don't get mapped to each other
    public static int BAUBLES_SLOT_START = -1;
    public static int TINKERS_SLOT_START = -1;
    public static int TG_SLOT_START = -1;
    public static int GC_SLOT_START = -1;

    private static final int CRAFTING_SLOT_X_OFFSET = 162;

    private InventoryBaubles baubles;
    private ArmorExtended tinkers;
    private InventoryTG travellers;
    private InventoryExtended gc;
    public static ArrayList<Pair<Integer, Integer>> nullSlots = null;

    public ContainerPutin(InventoryPlayer inventoryPlayer, boolean p_i1819_2_, EntityPlayer player) {
        super(inventoryPlayer, p_i1819_2_, player);
        this.inventorySlots.clear();
        ArrayList<Pair<Integer, Integer>> nullSlots = new ArrayList<>();

        /**=========================================================================================================
         * Vanilla Slots + Putinventory
         *========================================================================================================*/
        this.addSlotToContainer(new SlotCrafting(inventoryPlayer.player, this.craftMatrix, this.craftResult, 0, 144 + CRAFTING_SLOT_X_OFFSET, 36));
        int i;
        int j;

        //Crafting grid
        for (i = 0; i < 2; ++i) {
            for (j = 0; j < 2; ++j) {
                this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 2, 88 + CRAFTING_SLOT_X_OFFSET + j * 18, 26 + i * 18));
            }
        }

        //Armor slots
        for (i = 0; i < 4; ++i) {
            final int k = i;
            this.addSlotToContainer(new Slot(inventoryPlayer, inventoryPlayer.getSizeInventory() - 1 - i, 8, 8 + i * 18) {
                private static final String __OBFID = "CL_00001755";
                /**
                 * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1
                 * in the case of armor slots)
                 */
                public int getSlotStackLimit()
                {
                    return 1;
                }
                /**
                 * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
                 */
                public boolean isItemValid(ItemStack itemStack) {
                    if (itemStack == null) return false;
                    return itemStack.getItem().isValidArmor(itemStack, k, player);
                }
                /**
                 * Returns the icon index on items.png that is used as background image of the slot.
                 */
                @SideOnly(Side.CLIENT)
                public IIcon getBackgroundIconIndex()
                {
                    return ItemArmor.func_94602_b(k);
                }
            });
        }

        //main inventory
        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 18; ++j) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 18 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        //left half of hotbar
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }

        //right half of hotbar
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i + 63, 8 + (i + 9) * 18, 142));
        }

        this.onCraftMatrixChanged(this.craftMatrix);

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

            this.addSlotToContainer(new SlotPutin(tinkers, 0, xOffset, 8 + 0 * 18, player, TINKERS_MASK));
            this.addSlotToContainer(new SlotPutin(tinkers, 1, xOffset, 8 + 1 * 18, player, TINKERS_GLOVE));
            this.addSlotToContainer(new SlotPutin(tinkers, 3, xOffset, 8 + 2 * 18, player, TINKERS_BELT));
            this.addSlotToContainer(new SlotPutin(tinkers, 2, xOffset, 8 + 3 * 18, player, TINKERS_KNAPSACK));
            xOffset += 18;

            this.addSlotToContainer(new SlotPutin(tinkers, 6, xOffset, 8 + 0 * 18, player, TINKERS_HEART_RED));
            this.addSlotToContainer(new SlotPutin(tinkers, 5, xOffset, 8 + 1 * 18, player, TINKERS_HEART_YELLOW));
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
                this.addSlotToContainer(new SlotPutin(gc, 6, xOffset, 8 + 0 * 18, player, GC_THERMAL_HELM));
                this.addSlotToContainer(new SlotPutin(gc, 7, xOffset, 8 + 1 * 18, player, GC_THERMAL_CHEST));
                this.addSlotToContainer(new SlotPutin(gc, 8, xOffset, 8 + 2 * 18, player, GC_THERMAL_LEGS));
                this.addSlotToContainer(new SlotPutin(gc, 9, xOffset, 8 + 3 * 18, player, GC_THERMAL_BOOTS));
                xOffset += 18;

                this.addSlotToContainer(new SlotPutin(gc, 4, xOffset, 8 + 0 * 18, player, GC_PARACHUTE));
                this.addSlotToContainer(new SlotPutin(gc, 0, xOffset, 8 + 1 * 18, player, GC_OXYGEN_MASK));
                this.addSlotToContainer(new SlotPutin(gc, 2, xOffset, 8 + 2 * 18, player, GC_OXYGEN_TANK));
                nullSlots.add(Pair.of(xOffset, 8 + 3 * 18));
                xOffset += 18;

                this.addSlotToContainer(new SlotPutin(gc, 5, xOffset, 8 + 0 * 18, player, GC_FREQUENCY_MODULE));
                this.addSlotToContainer(new SlotPutin(gc, 1, xOffset, 8 + 1 * 18, player, GC_OXYGEN_GEAR));
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
