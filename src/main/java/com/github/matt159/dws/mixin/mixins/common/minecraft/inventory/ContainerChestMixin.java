package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(ContainerChest.class)
public abstract class ContainerChestMixin extends Container {

    @ModifyConstant(method = "<init>",
                    constant = @Constant(   intValue = 8,
                                            ordinal = 0),
                    require = 1)
    private int modifyChestSlotsXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "<init>",
                    slice = @Slice( from =  @At(value = "INVOKE",
                                                target = "Lnet/minecraft/inventory/ContainerChest;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
                                                ordinal = 0,
                                                shift = At.Shift.AFTER),
                                    to =    @At("RETURN")),
                    constant = @Constant(intValue = 9),
                    require = 3)
    private int modifyPlayerInventorySlotCount(int constant) {
        return 18;
    }
}
