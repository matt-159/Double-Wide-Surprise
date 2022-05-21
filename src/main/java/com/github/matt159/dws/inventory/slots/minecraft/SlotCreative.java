package com.github.matt159.dws.inventory.slots.minecraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.inventory.Slot;

@SideOnly(Side.CLIENT)
public class SlotCreative extends GuiContainerCreative.CreativeSlot {

    public SlotCreative(GuiContainerCreative gcc, Slot slot, int slotIndex) {
        gcc.super(slot, slotIndex);
    }
}
