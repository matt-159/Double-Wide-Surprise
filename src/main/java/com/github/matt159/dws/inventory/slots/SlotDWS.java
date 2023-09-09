package com.github.matt159.dws.inventory.slots;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import lombok.val;
import micdoodle8.mods.galacticraft.api.item.IItemThermal;
import micdoodle8.mods.galacticraft.core.items.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import tconstruct.library.accessory.IAccessory;
import travellersgear.api.ITravellersGear;

public class SlotDWS extends Slot {
    public int slotLimit = 1;
    public final SlotType type;
    private final EntityPlayer player;

    public SlotDWS(IInventory iinv, int id, int x, int y, EntityPlayer player, SlotType type) {
        super(iinv, id, x, y);
        this.player = player;
        this.type = type;

        this.slotLimit = this.type.getStackSize();
        this.texture = this.type.getSlotHintTexture();
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        if (itemStack == null || itemStack.getItem() == null) {
            return false;
        }

        val item = itemStack.getItem();

        switch (this.type) {
            case TRAVEL_CLOAK:
            case TRAVEL_PAULDRON:
            case TRAVEL_VAMBRACE:
            case TRAVEL_TITLE:
                if (item instanceof ITravellersGear) {
                    val travellersSlot = ((ITravellersGear) item).getSlot(itemStack);

                    return travellersSlot == this.type.ordinal() - SlotType.TRAVEL_CLOAK.ordinal();
                }

                return false;

            case BAUBLE_AMULET:
                return item instanceof IBauble &&
                        ((IBauble) item).getBaubleType(itemStack) == BaubleType.AMULET &&
                        ((IBauble) item).canEquip(itemStack, this.player);
            case BAUBLE_RING:
                return item instanceof IBauble &&
                        ((IBauble) item).getBaubleType(itemStack) == BaubleType.RING &&
                        ((IBauble) item).canEquip(itemStack, this.player);
            case BAUBLE_BELT:
                return item instanceof IBauble &&
                        ((IBauble) item).getBaubleType(itemStack) == BaubleType.BELT &&
                        ((IBauble) item).canEquip(itemStack, this.player);

            case TINKERS_GLOVE:
                return item instanceof IAccessory &&
                        ((IAccessory) item).canEquipAccessory(itemStack, 1);
            case TINKERS_KNAPSACK:
                return item instanceof IAccessory &&
                        ((IAccessory) item).canEquipAccessory(itemStack, 2);
            case TINKERS_BELT:
                return item instanceof IAccessory &&
                        ((IAccessory) item).canEquipAccessory(itemStack, 3);
            case TINKERS_MASK:
                return item instanceof IAccessory &&
                        ((IAccessory) item).canEquipAccessory(itemStack, 0);

            case TINKERS_HEART_RED:
                return item instanceof IAccessory &&
                        ((IAccessory) item).canEquipAccessory(itemStack, 6);
            case TINKERS_HEART_YELLOW:
                return item instanceof IAccessory &&
                        ((IAccessory) item).canEquipAccessory(itemStack, 5);
            case TINKERS_HEART_GREEN:
                return item instanceof IAccessory &&
                        ((IAccessory) item).canEquipAccessory(itemStack, 4);

            case GC_THERMAL_HELM:
            case GC_THERMAL_CHEST:
            case GC_THERMAL_LEGS:
            case GC_THERMAL_BOOTS:
                return item instanceof IItemThermal &&
                        ((IItemThermal) item).isValidForSlot(itemStack, this.type.ordinal() - SlotType.GC_THERMAL_HELM.ordinal());

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

            default:
                return false;
        }
    }

    @Override
    public int getSlotStackLimit() {
        return this.slotLimit;
    }

    @Override
    public boolean canTakeStack(EntityPlayer player) {
        if (this.getStack() == null) {
            return false;
        }

        val item = this.getStack().getItem();
        if (item instanceof IBauble) {
            val bauble = (IBauble) item;

            switch(this.type) {
                case BAUBLE_BELT:
                    return bauble.getBaubleType(getStack()) == BaubleType.BELT &&
                           bauble.canUnequip(getStack(), this.player);
                case BAUBLE_AMULET:
                    return bauble.getBaubleType(getStack()) == BaubleType.AMULET &&
                           bauble.canUnequip(getStack(), this.player);
                case BAUBLE_RING:
                    return bauble.getBaubleType(getStack()) == BaubleType.RING &&
                           bauble.canUnequip(getStack(), this.player);
                default:
                    return true;
            }
        }

        return false;
    }
}
