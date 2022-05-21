package com.github.matt159.dws.inventory.slots.minecraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotHorseArmor extends Slot {

    final EntityHorse horse;

    public SlotHorseArmor(EntityHorse horse, IInventory horseInventory, int slotIndex, int xDisplayPosition, int yDisplayPosition) {
        super(horseInventory, slotIndex, xDisplayPosition, yDisplayPosition);
        this.horse = horse;
    }

    public boolean isItemValid(ItemStack itemStack) {
        return super.isItemValid(itemStack) && this.horse.func_110259_cr() && EntityHorse.func_146085_a(itemStack.getItem());
    }

    @SideOnly(Side.CLIENT)
    public boolean func_111238_b() {
        return this.horse.func_110259_cr();
    }
}
