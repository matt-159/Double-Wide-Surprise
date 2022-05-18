package com.github.thebrochacho.dws.mixins.common.minecraft.inventory;

import com.github.thebrochacho.dws.inventory.slots.minecraft.SlotBeacon;
import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.tileentity.TileEntityBeacon;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerBeacon.class)
public class ContainerBeaconMixin extends Container {

    @Mutable
    @Shadow @Final private ContainerBeacon.BeaconSlot beaconSlot;

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void addSlotsToContainer(InventoryPlayer inventoryPlayer, TileEntityBeacon tileEntityBeacon, CallbackInfo ci) {
        ((ContainerBeacon) (Object) (this)).inventorySlots.clear();

        this.beaconSlot = new SlotBeacon((ContainerBeacon) (Object) (this), tileEntityBeacon, 0, 190, 110);
        this.addSlotToContainer(beaconSlot);

        DWSUtil.addDWSSlotsToContainer((ContainerBeacon) (Object) (this), inventoryPlayer, 8, 137, 195);
    }


    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
