package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import com.github.matt159.dws.inventory.slots.minecraft.SlotBeacon;
import com.github.matt159.dws.util.DWSUtil;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.tileentity.TileEntityBeacon;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerBeacon.class)
public abstract class ContainerBeaconMixin extends Container {

//    @Mutable
//    @Shadow @Final private ContainerBeacon.BeaconSlot beaconSlot;
//
//    @Inject(method = "<init>",
//            at = @At(value = "RETURN"),
//            require = 1)
//    private void addSlotsToContainer(InventoryPlayer inventoryPlayer, TileEntityBeacon tileEntityBeacon, CallbackInfo ci) {
//        this.inventorySlots.clear();
//
//        this.beaconSlot = new SlotBeacon((ContainerBeacon) (Object) (this), tileEntityBeacon, 0, 190, 110);
//        this.addSlotToContainer(beaconSlot);
//
//        DWSUtil.addDWSSlotsToContainer((ContainerBeacon) (Object) (this), inventoryPlayer, 8, 137, 195);
//    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 136),
                    require = 1)
    private int modifyBeaconSlotXDisplayPosition(int constant) {
        return constant + 54;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 36),
                    require = 1)
    private int modifyXOffset(int constant) {
        return 8;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
