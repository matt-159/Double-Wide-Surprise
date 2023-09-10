package com.github.matt159.dws.inventory.slots.compat;

import com.github.matt159.dws.inventory.slots.SlotDWS;
import com.github.matt159.dws.inventory.slots.SlotType;
import lombok.val;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import travellersgear.api.ITravellersGear;

public class SlotTravellersGearCompat extends SlotDWS {
    public SlotTravellersGearCompat(IInventory inventory, int id, int x, int y, SlotType type) {
        super(inventory, id, x, y, type);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        val item = itemStack.getItem();

        if (item instanceof ITravellersGear) {
            val travellersGear = (ITravellersGear) item;
            val travellersSlot = travellersGear.getSlot(itemStack);

            return travellersSlot == this.type.ordinal() - SlotType.TRAVEL_CLOAK.ordinal();
        }

        return false;
    }
}
