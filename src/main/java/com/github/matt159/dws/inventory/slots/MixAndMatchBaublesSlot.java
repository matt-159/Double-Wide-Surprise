package com.github.matt159.dws.inventory.slots;

import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.IBaubleExpanded;
import com.github.matt159.dws.config.DWSConfig;
import lombok.val;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.HashMap;

public class MixAndMatchBaublesSlot extends SlotDWS {
    private final EntityPlayer player;

    public MixAndMatchBaublesSlot(EntityPlayer player, IInventory inventory, int id, int x, int y) {
        super(inventory, id, x, y, SlotType.ANY);
        this.player = player;
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }

        val item = itemStack.getItem();

        String[] slotTypes;

        if (item instanceof IBaubleExpanded) {
            slotTypes = ((IBaubleExpanded) item).getBaubleTypes(itemStack);
        } else if (item instanceof IBauble) {
            slotTypes = new String[] { ((IBauble) item).getBaubleType(itemStack).toString().toLowerCase() };
        } else {
            return false;
        }

        if (slotTypes.length == 0) {
            return false;
        }

        val equippedBaubles = BaublesApi.getBaubles(this.player);

        val typeFrequency = new HashMap<String, Long>();
        for (int i = 0; i < equippedBaubles.getSizeInventory(); i++) {
            val stackInSlot = equippedBaubles.getStackInSlot(i);

            if (stackInSlot == null || stackInSlot.getItem() == null || this.getSlotIndex() == i) {
                continue;
            }

            val itemInSlot = stackInSlot.getItem();

            String[] baubleTypesInSlot;

            if (itemInSlot instanceof IBaubleExpanded) {
                baubleTypesInSlot = ((IBaubleExpanded) itemInSlot).getBaubleTypes(stackInSlot);
            } else {
                val baubleType = BaubleExpandedSlots.getTypeFromBaubleType(((IBauble) itemInSlot).getBaubleType(stackInSlot));
                baubleTypesInSlot = new String[] { baubleType.toLowerCase() };
            }

            for (val baubleType : baubleTypesInSlot) {
                val frequency = 1 + typeFrequency.computeIfAbsent(baubleType, key -> 0L);

                typeFrequency.put(baubleType, frequency);
            }
        }

        boolean canEquip = true;
        for (val slotType : slotTypes) {
            if (typeFrequency.getOrDefault(slotType, 0L) >= DWSConfig.SLOT_GROUPINGS.getOrDefault(slotType, 0L)) {
                canEquip = false;

                break;
            }
        }

        return canEquip;
    }
}
