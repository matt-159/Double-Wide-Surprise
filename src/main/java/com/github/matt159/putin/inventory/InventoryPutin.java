package com.github.matt159.putin.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class InventoryPutin extends InventoryPlayer {

    public ItemStack[] armorInventory = new ItemStack[4];

    public ItemStack[] baublesInventory = new ItemStack[4];
    public ItemStack[] tinkersInventory = new ItemStack[7];
    public ItemStack[] travellersGearInventory = new ItemStack[5];

    public InventoryPutin(EntityPlayer p_i1750_1_) {
        super(p_i1750_1_);
        this.mainInventory = new ItemStack[72];
    }
}
