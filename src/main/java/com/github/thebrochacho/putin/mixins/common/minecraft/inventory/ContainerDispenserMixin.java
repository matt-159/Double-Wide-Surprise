package com.github.thebrochacho.putin.mixins.common.minecraft.inventory;

import com.github.thebrochacho.putin.util.PutinUtil;
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
            at = @At(value = "RETURN"),
            require = 1)
    private void addSlotsToContainer(IInventory inventoryPlayer, TileEntityDispenser tileEntityDispenser, CallbackInfo ci) {
        ((ContainerDispenser) (Object) (this)).inventorySlots.clear();

        int row;
        int col;
        for(row = 0; row < 3; ++row) {
            for(col = 0; col < 3; ++col) {
                this.addSlotToContainer(new Slot(tileEntityDispenser, col + row * 3, 143 + col * 18, 17 + row * 18));
            }
        }

        PutinUtil.addPutinSlotsToContainer((ContainerDispenser) (Object) (this), inventoryPlayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
