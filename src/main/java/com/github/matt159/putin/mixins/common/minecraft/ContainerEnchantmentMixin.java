package com.github.matt159.putin.mixins.common.minecraft;

import com.github.matt159.putin.inventory.SlotEnchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerEnchantment.class)
public class ContainerEnchantmentMixin extends Container {

    @Shadow public IInventory tableInventory;

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void addSlotsToContainer(InventoryPlayer inventoryPlayer, World p_i1811_2_, int p_i1811_3_, int p_i1811_4_, int p_i1811_5_, CallbackInfo ci) {
        ((ContainerEnchantment) (Object) (this)).inventorySlots.clear();
        this.addSlotToContainer(new SlotEnchantment(tableInventory, 0, 106, 47));

        int row, col;
        //main inventory
        for (row = 0; row < 3; ++row) {
            for (col = 0; col < 18; ++col) {
                this.addSlotToContainer(new Slot(inventoryPlayer, col + row * 18 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        //left half of hotbar
        for (row = 0; row < 9; ++row) {
            this.addSlotToContainer(new Slot(inventoryPlayer, row, 8 + row * 18, 142));
        }

        //right half of hotbar
        for (row = 0; row < 9; ++row) {
            this.addSlotToContainer(new Slot(inventoryPlayer, row + 63, 8 + (row + 9) * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
