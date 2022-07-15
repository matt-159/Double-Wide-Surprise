package com.github.thebrochacho.dws.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;

public class ContainerDWS extends Container {
    //Offset so that itemslots don't get mapped to each other
    private static final int CRAFTING_SLOT_X_OFFSET = 162;

    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);
    public IInventory craftResult = new InventoryCraftResult();
    public boolean isLocalWorld;
    private final EntityPlayer thePlayer;

    public static ArrayList<Pair<Integer, Integer>> nullSlots = null;

    public ContainerDWS(InventoryPlayer inventoryPlayer, boolean p_i1819_2_, EntityPlayer player) {
        this.isLocalWorld = p_i1819_2_;
        this.thePlayer = player;

        this.inventorySlots.clear();

        /*=========================================================================================================
         * Vanilla Slots + DWSInventory
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
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);

    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) {
        ItemStack itemStack = null;
        final Slot slot = (Slot) this.inventorySlots.get(slotNumber);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStackInSlot = slot.getStack();
            itemStack = itemStackInSlot.copy();

            if (slotNumber >= 0 && slotNumber < 9) { //Crafting Matrix + Crafting Result + Armor Slots
                if (!mergeItemStack(itemStackInSlot, 9, 81, false))
                    return null;
            }
            else if (((itemStack.getItem() instanceof ItemArmor)) && (!((Slot)this.inventorySlots.get(((ItemArmor)itemStack.getItem()).armorType)).getHasStack())) {
                int armorType = 5 + ((ItemArmor)itemStack.getItem()).armorType;
                if (!mergeItemStack(itemStackInSlot, armorType, armorType + 1, false))
                    return null;
            } else if (slotNumber < 63) {
                //main inventory to hotbar
                if (!this.mergeItemStack(itemStackInSlot, 63, 81, false)) {
                    return null;
                }
            } else if (slotNumber < 81) {
                //hotbar to main inventory
                if (!this.mergeItemStack(itemStackInSlot, 9, 63, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemStackInSlot, 9, 81, false)) {
                return null;
            }

            if (itemStackInSlot.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (itemStackInSlot.stackSize == itemStack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemStackInSlot);
        }

        return itemStack;
    }

    @Override
    public void putStacksInSlots(ItemStack[] p_75131_1_) {
        super.putStacksInSlots(p_75131_1_);
    }
}
