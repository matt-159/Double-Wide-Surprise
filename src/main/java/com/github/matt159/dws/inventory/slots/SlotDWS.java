package com.github.matt159.dws.inventory.slots;

import com.github.matt159.dws.DoubleWideSurprise;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class SlotDWS extends Slot {
    public int slotLimit;
    public final SlotType type;

    private final ResourceLocation slotHintTexture;

    public SlotDWS(IInventory inventory, int id, int x, int y, SlotType type) {
        super(inventory, id, x, y);
        this.type = type;

        this.slotLimit = this.type.getStackSize();
        this.slotHintTexture = DoubleWideSurprise.proxy.getSlotHintTexture(type);
    }

    @Override
    public abstract boolean isItemValid(ItemStack itemStack);

    @Override
    public final int getSlotStackLimit() {
        return this.slotLimit;
    }

    @Override
    public ResourceLocation getBackgroundIconTexture() {
        return this.slotHintTexture;
    }
}
