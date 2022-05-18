package com.github.matt159.dws.inventory.slots;

import baubles.api.BaubleType;
import baubles.api.IBauble;
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
    public SlotType type;
    private EntityPlayer player;

    public SlotDWS(IInventory iinv, int id, int x, int y, EntityPlayer player, SlotType type) {
        super(iinv, id, x, y);
        this.player = player;
        this.type = type;

        if (this.type.equals(SlotType.TINKERS_HEART_RED) ||
            this.type.equals(SlotType.TINKERS_HEART_YELLOW) ||
            this.type.equals(SlotType.TINKERS_HEART_GREEN)) {
            this.slotLimit = 10;
        }
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        if (itemStack == null || itemStack.getItem() == null)
            return false;

        switch (this.type) {
            case VANILLA_HELM:
            case VANILLA_CHEST:
            case VANILLA_LEGS:
            case VANILLA_BOOTS:
                return itemStack.getItem().isValidArmor(itemStack, this.type.ordinal(), player);

            case TRAVEL_CLOAK:
            case TRAVEL_PAULDRON:
            case TRAVEL_VAMBRACE:
            case TRAVEL_TITLE:
                return (itemStack.getItem() instanceof ITravellersGear) &&
                        ((ITravellersGear)itemStack.getItem()).getSlot(itemStack) == this.type.ordinal() - 4;

            case BAUBLE_AMULET:
                return itemStack.getItem() instanceof IBauble &&
                        ((IBauble)itemStack.getItem()).getBaubleType(itemStack) == BaubleType.AMULET &&
                        ((IBauble)itemStack.getItem()).canEquip(itemStack, this.player);
            case BAUBLE_RING:
                return itemStack.getItem() instanceof IBauble &&
                        ((IBauble)itemStack.getItem()).getBaubleType(itemStack) == BaubleType.RING &&
                        ((IBauble)itemStack.getItem()).canEquip(itemStack, this.player);
            case BAUBLE_BELT:
                return itemStack.getItem() instanceof IBauble &&
                        ((IBauble)itemStack.getItem()).getBaubleType(itemStack) == BaubleType.BELT &&
                        ((IBauble)itemStack.getItem()).canEquip(itemStack, this.player);

            case TINKERS_GLOVE:
                return itemStack.getItem() instanceof IAccessory &&
                        ((IAccessory)itemStack.getItem()).canEquipAccessory(itemStack, 1);
            case TINKERS_KNAPSACK:
                return itemStack.getItem() instanceof IAccessory &&
                        ((IAccessory)itemStack.getItem()).canEquipAccessory(itemStack, 2);
            case TINKERS_BELT:
                return itemStack.getItem() instanceof IAccessory &&
                        ((IAccessory)itemStack.getItem()).canEquipAccessory(itemStack, 3);
            case TINKERS_MASK:
                return itemStack.getItem() instanceof IAccessory &&
                        ((IAccessory)itemStack.getItem()).canEquipAccessory(itemStack, 0);

            case TINKERS_HEART_RED:
                return itemStack.getItem() instanceof IAccessory &&
                        ((IAccessory)itemStack.getItem()).canEquipAccessory(itemStack, 6);
            case TINKERS_HEART_YELLOW:
                return itemStack.getItem() instanceof IAccessory &&
                        ((IAccessory)itemStack.getItem()).canEquipAccessory(itemStack, 5);
            case TINKERS_HEART_GREEN:
                return itemStack.getItem() instanceof IAccessory &&
                        ((IAccessory)itemStack.getItem()).canEquipAccessory(itemStack, 4);

            case GC_THERMAL_HELM:
            case GC_THERMAL_CHEST:
            case GC_THERMAL_LEGS:
            case GC_THERMAL_BOOTS:
                return itemStack.getItem() instanceof IItemThermal &&
                        ((IItemThermal)itemStack.getItem()).isValidForSlot(itemStack, this.type.ordinal() - 18);

            case GC_FREQUENCY_MODULE:
                return itemStack.getItem() == GCItems.basicItem &&
                        itemStack.getItemDamage() == 19;
            case GC_OXYGEN_MASK:
                return itemStack.getItem() instanceof ItemOxygenMask;
            case GC_OXYGEN_GEAR:
                return itemStack.getItem() instanceof ItemOxygenGear;
            case GC_OXYGEN_TANK:
                return itemStack.getItem() instanceof ItemOxygenTank;
            case GC_PARACHUTE:
                return itemStack.getItem() instanceof ItemParaChute;

            default:
                return false;
        }
    }

    @Override
    public int getSlotStackLimit()
    {
        return this.slotLimit;
    }

    @Override
    public boolean canTakeStack(EntityPlayer player)
    {
        if(getStack()==null)
            return false;
        switch(this.type)
        {
            case BAUBLE_BELT:
                return getStack().getItem() instanceof IBauble &&
                        ((IBauble)getStack().getItem()).getBaubleType(getStack()) == BaubleType.BELT &&
                        ((IBauble)getStack().getItem()).canUnequip(getStack(), this.player);
            case BAUBLE_AMULET:
                return getStack().getItem() instanceof IBauble &&
                        ((IBauble)getStack().getItem()).getBaubleType(getStack()) == BaubleType.AMULET &&
                        ((IBauble)getStack().getItem()).canUnequip(getStack(), this.player);
            case BAUBLE_RING:
                return getStack().getItem() instanceof IBauble &&
                        ((IBauble)getStack().getItem()).getBaubleType(getStack()) == BaubleType.RING &&
                        ((IBauble)getStack().getItem()).canUnequip(getStack(), this.player);
            default:
                return true;
        }
    }

    public enum SlotType
    {
        VANILLA_HELM,
        VANILLA_CHEST,
        VANILLA_LEGS,
        VANILLA_BOOTS,

        TRAVEL_CLOAK,
        TRAVEL_PAULDRON,
        TRAVEL_VAMBRACE,
        TRAVEL_TITLE,

        BAUBLE_AMULET,
        BAUBLE_RING,
        BAUBLE_BELT,

        TINKERS_GLOVE,
        TINKERS_KNAPSACK,
        TINKERS_BELT,
        TINKERS_MASK,
        TINKERS_HEART_RED,
        TINKERS_HEART_YELLOW,
        TINKERS_HEART_GREEN,

        GC_THERMAL_HELM,
        GC_THERMAL_CHEST,
        GC_THERMAL_LEGS,
        GC_THERMAL_BOOTS,
        GC_FREQUENCY_MODULE,
        GC_OXYGEN_MASK,
        GC_OXYGEN_GEAR,
        GC_OXYGEN_TANK,
        GC_PARACHUTE;
    }
}
