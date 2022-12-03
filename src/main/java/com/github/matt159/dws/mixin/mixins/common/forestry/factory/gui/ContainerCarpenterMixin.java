package com.github.matt159.dws.mixin.mixins.common.forestry.factory.gui;

import forestry.factory.gui.ContainerCarpenter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerCarpenter.class)
public abstract class ContainerCarpenterMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 8,
                                           ordinal = 1),
                                 @Constant(intValue = 10,
                                           ordinal = 1),
                                 @Constant(intValue = 80),
                                 @Constant(intValue = 83),
                                 @Constant(intValue = 120) },
                    remap = false,
                    require = 6)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
