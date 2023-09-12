package com.github.matt159.dws.inventory.slots.compat;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.github.matt159.dws.inventory.slots.SlotDWS;
import com.github.matt159.dws.inventory.slots.SlotType;
import lombok.val;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotBaublesCompat extends SlotDWS {
    private final EntityPlayer player;

    public SlotBaublesCompat(IInventory inventory, int id, int x, int y, EntityPlayer player, SlotType type) {
        super(inventory, id, x, y, type);
        this.player = player;
    }

    @Override
    public boolean canTakeStack(EntityPlayer player) {
        return super.canTakeStack(player);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }

        if (itemStack.getItem() instanceof IBauble) {
            val bauble = (IBauble) itemStack.getItem();
            val baubleType = bauble.getBaubleType(itemStack);

            switch(this.type) {
                case BAUBLE_BELT:
                    return baubleType == BaubleType.BELT && bauble.canUnequip(this.getStack(), this.player);
                case BAUBLE_AMULET:
                    return baubleType == BaubleType.AMULET && bauble.canUnequip(this.getStack(), this.player);
                case BAUBLE_RING:
                    return baubleType == BaubleType.RING && bauble.canUnequip(this.getStack(), this.player);
            }
        }

        return false;
    }
}
