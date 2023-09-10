package com.github.matt159.dws.inventory.slots.compat;

import com.github.matt159.dws.inventory.slots.SlotDWS;
import com.github.matt159.dws.inventory.slots.SlotType;
import lombok.val;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import tconstruct.library.accessory.IAccessory;

public class SlotTinkersCompat extends SlotDWS {
    public SlotTinkersCompat(IInventory inventory, int id, int x, int y, SlotType type) {
        super(inventory, id, x, y, type);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        val item = itemStack.getItem();

        if (item instanceof IAccessory) {
            val accessory = ((IAccessory) item);

            switch (this.type) {
                case TINKERS_GLOVE:
                    return accessory.canEquipAccessory(itemStack, 1);
                case TINKERS_KNAPSACK:
                    return accessory.canEquipAccessory(itemStack, 2);
                case TINKERS_BELT:
                    return accessory.canEquipAccessory(itemStack, 3);
                case TINKERS_MASK:
                    return accessory.canEquipAccessory(itemStack, 0);

                case TINKERS_HEART_RED:
                    return accessory.canEquipAccessory(itemStack, 6);
                case TINKERS_HEART_YELLOW:
                    return accessory.canEquipAccessory(itemStack, 5);
                case TINKERS_HEART_GREEN:
                    return accessory.canEquipAccessory(itemStack, 4);
            }
        }
        return false;
    }
}
