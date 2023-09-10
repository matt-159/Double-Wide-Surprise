package com.github.matt159.dws.inventory.slots.compat;

import com.github.matt159.dws.inventory.slots.SlotDWS;
import com.github.matt159.dws.inventory.slots.SlotType;
import lombok.val;
import micdoodle8.mods.galacticraft.api.item.IItemThermal;
import micdoodle8.mods.galacticraft.core.items.GCItems;
import micdoodle8.mods.galacticraft.core.items.ItemOxygenGear;
import micdoodle8.mods.galacticraft.core.items.ItemOxygenMask;
import micdoodle8.mods.galacticraft.core.items.ItemOxygenTank;
import micdoodle8.mods.galacticraft.core.items.ItemParaChute;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotGalacticraftCompat extends SlotDWS {
    public SlotGalacticraftCompat(IInventory inventory, int id, int x, int y, SlotType type) {
        super(inventory, id, x, y, type);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }

        val item = itemStack.getItem();

        switch (this.type) {
            case GC_THERMAL_HELM:
            case GC_THERMAL_CHEST:
            case GC_THERMAL_LEGS:
            case GC_THERMAL_BOOTS:
                if (item instanceof IItemThermal) {
                    val thermalItem = (IItemThermal) item;
                    val thermalArmorSlot = this.type.ordinal() - SlotType.GC_THERMAL_HELM.ordinal();

                    return thermalItem.isValidForSlot(itemStack, thermalArmorSlot);
                }
                break;

            case GC_FREQUENCY_MODULE:
                return item == GCItems.basicItem &&
                        itemStack.getItemDamage() == 19;
            case GC_OXYGEN_MASK:
                return item instanceof ItemOxygenMask;
            case GC_OXYGEN_GEAR:
                return item instanceof ItemOxygenGear;
            case GC_OXYGEN_TANK:
                return item instanceof ItemOxygenTank;
            case GC_PARACHUTE:
                return item instanceof ItemParaChute;
        }

        return false;
    }
}
