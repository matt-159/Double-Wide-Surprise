package com.github.thebrochacho.dws.mixin.mixins.common.forestry.core.circuits;

import forestry.core.circuits.ContainerSolderingIron;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(ContainerSolderingIron.class)
public abstract class ContainerSolderingIronMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 12,
                                           slice = "12"),
                                 @Constant(intValue = 152,
                                           slice = "") },
                    slice = @Slice(from = @At(value = "INVOKE",
                                              target = "Lforestry/core/circuits/ContainerSolderingIron;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
                                              ordinal = 1),
                                   to = @At("TAIL"),
                                   id = "12"),
                    require = 6)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
