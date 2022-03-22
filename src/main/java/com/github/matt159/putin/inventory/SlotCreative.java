package com.github.matt159.putin.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

@SideOnly(Side.CLIENT)
public class SlotCreative extends GuiContainerCreative.CreativeSlot {
//    private final Slot slot;
//    final GuiContainerCreative container;

    public SlotCreative(GuiContainerCreative gcc, Slot slot, int slotIndex) {
        gcc.super(slot, slotIndex);
//        this.container = gcc;
//        this.slot = slot;
    }

//    public void onPickupFromSlot(EntityPlayer player, ItemStack itemStack) {
//        this.slot.onPickupFromSlot(player, itemStack);
//    }
//
//    public boolean isItemValid(ItemStack itemStack) {
//        return this.slot.isItemValid(itemStack);
//    }
//
//    public ItemStack getStack() {
//        return this.slot.getStack();
//    }
//
//    public boolean getHasStack() {
//        return this.slot.getHasStack();
//    }
//
//    public void putStack(ItemStack itemStack) {
//        this.slot.putStack(itemStack);
//    }
//
//    public void onSlotChanged() {
//        this.slot.onSlotChanged();
//    }
//
//    public int getSlotStackLimit() {
//        return this.slot.getSlotStackLimit();
//    }
//
//    public IIcon getBackgroundIconIndex() {
//        return this.slot.getBackgroundIconIndex();
//    }
//
//    public ItemStack decrStackSize(int amount) {
//        return this.slot.decrStackSize(amount);
//    }
//
//    public boolean isSlotInInventory(IInventory inventory, int slotIndex) {
//        return this.slot.isSlotInInventory(inventory, slotIndex);
//    }
}
