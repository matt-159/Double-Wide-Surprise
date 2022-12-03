package com.github.matt159.dws.mixin.mixins.common.forestry.apiculture.gui;

import forestry.apiculture.gui.ContainerImprinter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerImprinter.class)
public abstract class ContainerImprinterMixin {
    @ModifyConstant(method = "<init>",
            constant = @Constant(intValue = 152),
            remap = false,
            require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
