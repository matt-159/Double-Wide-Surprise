package com.github.thebrochacho.dws.mixins.common.minecraft.inventory;

import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerHopper.class)
public class ContainerHopperMixin extends Container {

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void addSlotsToContainer(InventoryPlayer inventoryPlayer, IInventory inventoryHopper, CallbackInfo ci) {
        ((ContainerHopper) (Object) (this)).inventorySlots.clear();

        int var4;
        for(var4 = 0; var4 < inventoryHopper.getSizeInventory(); ++var4) {
            this.addSlotToContainer(new Slot(inventoryHopper, var4, 125 + var4 * 18, 20));
        }

        DWSUtil.addDWSSlotsToContainer((ContainerHopper) (Object) (this), inventoryPlayer, 8, 51, 109);
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
