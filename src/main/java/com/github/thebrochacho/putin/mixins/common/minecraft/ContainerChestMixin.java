package com.github.thebrochacho.putin.mixins.common.minecraft;

import com.github.thebrochacho.putin.interfaces.minecraft.IContainerChestMixin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerChest.class)
public class ContainerChestMixin extends Container implements IContainerChestMixin {

    @Shadow private int numRows;

    @Inject(method = "<init>",
            at = @At(   value = "RETURN",
                        remap = false),
            require = 1)
    private void addSlotsToContainer(IInventory inventoryPlayer, IInventory inventoryChest, CallbackInfo ci) {
        ((ContainerChest) (Object) (this)).inventorySlots.clear();

        int var3 = (((IContainerChestMixin) (Object) (this)).getNumRows() - 4) * 18;

        int row;
        int col;
        for(row = 0; row < this.numRows; ++row) {
            for(col = 0; col < 9; ++col) {
                this.addSlotToContainer(new Slot(inventoryChest, col + row * 9, 89 + col * 18, 18 + row * 18));
            }
        }

        //main inventory
        for (row = 0; row < 3; ++row) {
            for (col = 0; col < 18; ++col) {
                this.addSlotToContainer(new Slot(inventoryPlayer, col + row * 18 + 9, 8 + col * 18, 103 + row * 18 + var3));
            }
        }

        //left half of hotbar
        for (row = 0; row < 9; ++row) {
            this.addSlotToContainer(new Slot(inventoryPlayer, row, 8 + row * 18, 161 + var3));
        }

        //right half of hotbar
        for (row = 0; row < 9; ++row) {
            this.addSlotToContainer(new Slot(inventoryPlayer, row + 63, 8 + (row + 9) * 18, 161 + var3));
        }
    }

    @Override
    public int getNumRows() {
        return numRows;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
