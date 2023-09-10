package com.github.matt159.dws.inventory.slots.compat;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.IBaubleExpanded;
import baubles.common.container.InventoryBaubles;
import com.github.matt159.dws.inventory.slots.SlotDWS;
import com.github.matt159.dws.inventory.slots.SlotType;
import lombok.val;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotBaublesExpandedCompat extends SlotDWS {
    private final String baubleType;
    public SlotBaublesExpandedCompat(IInventory inventory, int id, int x, int y, SlotType type, String baubleType) {
        super(inventory, id, x, y, type);
        this.baubleType = baubleType;
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }

        val item = itemStack.getItem();
        if (item instanceof IBauble) {
            val bauble = (IBauble) item;

            val player = (EntityLivingBase) ((InventoryBaubles) this.inventory).player.get();

            if (bauble.canEquip(itemStack, player)) {
                String[] types;
                if (bauble instanceof IBaubleExpanded) {
                    types = ((IBaubleExpanded) item).getBaubleTypes(itemStack);
                } else {
                    types = new String[] { BaubleExpandedSlots.getTypeFromBaubleType(bauble.getBaubleType(itemStack)) };
                }

                for (val type : types) {
                    if (this.baubleType.equals(type)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean canTakeStack(EntityPlayer player) {
        val itemStack = this.getStack();

        if (itemStack.getItem() instanceof IBauble) {
            val bauble = (IBauble) itemStack.getItem();

            return bauble.canUnequip(itemStack, player);
        }

        return false;
    }
}
