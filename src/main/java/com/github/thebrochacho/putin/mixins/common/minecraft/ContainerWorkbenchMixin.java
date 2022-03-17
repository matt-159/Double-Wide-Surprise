package com.github.thebrochacho.putin.mixins.common.minecraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerWorkbench.class)
public class ContainerWorkbenchMixin extends Container {

    @Inject(method = "<init>",
            at = @At(   value = "RETURN",
                        remap = false),
            require = 1)
    private void addSlotsToContainer(InventoryPlayer inventoryPlayer, World p_i1084_2_, int p_i1084_3_, int p_i1084_4_, int p_i1084_5_, CallbackInfo ci) {
        ((ContainerWorkbench) (Object) (this)).inventorySlots.clear();
        InventoryCrafting craftMatrix = ((ContainerWorkbench) (Object) (this)).craftMatrix;

        this.addSlotToContainer(new SlotCrafting(inventoryPlayer.player, craftMatrix, ((ContainerWorkbench) (Object) (this)).craftResult, 0, 205, 35));

        int row;
        int col;
        for(row = 0; row < 3; ++row) {
            for(col = 0; col < 3; ++col) {
                this.addSlotToContainer(new Slot(craftMatrix, col + row * 3, 111 + col * 18, 17 + row * 18));
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
