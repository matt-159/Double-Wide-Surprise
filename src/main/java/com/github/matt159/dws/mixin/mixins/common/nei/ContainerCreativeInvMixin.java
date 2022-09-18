package com.github.matt159.dws.mixin.mixins.common.nei;

import codechicken.nei.ContainerCreativeInv;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ContainerCreativeInv.class)
public abstract class ContainerCreativeInvMixin extends Container {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    remap = false,
                    require = 6)
    private int modifyInventorySize(int constant) {
        return 18;
    }

    @Redirect(  method = "<init>",
                at = @At(   value = "INVOKE",
                            target = "Lcodechicken/nei/ContainerCreativeInv;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;"),
                remap = false,
                require = 3)
    private Slot redirectAddSlotsToContainer(ContainerCreativeInv instance, Slot slot) {
        slot.xDisplayPosition += 23;

        return this.addSlotToContainer(slot);
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = {    @Constant(intValue = 54),
                                    @Constant(intValue = 90)    },
                    remap = false,
                    require = 6)
    private int modifySlotRangeChecks(int constant) {
        return constant * 2;
    }
}