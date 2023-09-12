package com.github.matt159.dws.inventory.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class SlotDWS extends Slot {
    public int slotLimit;
    public final SlotType type;

    public SlotDWS(IInventory inventory, int id, int x, int y, SlotType type) {
        super(inventory, id, x, y);
        this.type = type;

        this.slotLimit = this.type.getStackSize();
        this.texture = this.type.getSlotHintTexture();
    }

    @Override
    public abstract boolean isItemValid(ItemStack itemStack);

    @Override
    public final int getSlotStackLimit() {
        return this.slotLimit;
    }
}
