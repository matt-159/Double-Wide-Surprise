package com.github.thebrochacho.putin.mixins.common.minecraft;

import com.github.thebrochacho.putin.interfaces.IContainerChestMixin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.tileentity.TileEntityDispenser;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerDispenser.class)
public class ContainerDispenserMixin extends Container {

    @Inject(method = "<init>",
            at = @At(   value = "RETURN",
                    remap = false),
            require = 1)
    private void addSlotsToContainer(IInventory inventoryPlayer, TileEntityDispenser p_i1825_2_, CallbackInfo ci) {
        ((ContainerDispenser) (Object) (this)).inventorySlots.clear();

        int row;
        int col;
        for(row = 0; row < 3; ++row) {
            for(col = 0; col < 3; ++col) {
                this.addSlotToContainer(new Slot(p_i1825_2_, col + row * 3, 143 + col * 18, 17 + row * 18));
            }
        }

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
