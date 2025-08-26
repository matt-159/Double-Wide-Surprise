package com.github.matt159.dws.util;

import baubles.api.IBauble;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.IBaubleExpanded;
import com.github.matt159.dws.config.DWSConfig;
import lombok.val;

import net.minecraft.inventory.IInventory;

import java.util.HashMap;
import java.util.Map;

public class MixAndMatchSlotUtil {
    public static Map<String, Long> getAvailableTags(IInventory baublesInventory) {
        val tagLimit = DWSConfig.SLOT_GROUPINGS;

        val remainingTags = new HashMap<>(tagLimit);

        for (int i = 0; i < baublesInventory.getSizeInventory(); i++) {
            val itemStack = baublesInventory.getStackInSlot(i);

            if (itemStack == null || !(itemStack.getItem() instanceof IBauble)) {
                continue;
            }

            val item = itemStack.getItem();

            String[] types;
            if (item instanceof IBaubleExpanded) {
                types = ((IBaubleExpanded) item).getBaubleTypes(itemStack);
            } else {
                val baubleType = ((IBauble) item).getBaubleType(itemStack);
                types = new String[]{ BaubleExpandedSlots.getTypeFromBaubleType(baubleType) };
            }

            for (int j = 0; j < types.length; j++) {
                val type = types[j];

                val frequency = remainingTags.getOrDefault(type, 0L) - 1;

                if (frequency > 0) {
                    remainingTags.put(type, frequency);
                } else {
                    remainingTags.remove(type);
                }
            }
        }

        return remainingTags;
    }
}
