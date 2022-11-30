package com.github.matt159.dws.mixin.mixins.common.forestry.factory.gui;

import forestry.factory.gui.ContainerCentrifuge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerCentrifuge.class)
public abstract class ContainerCentrifugeMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 30),
                                 @Constant(intValue = 98) },
                    remap = false,
                    require = 2)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
