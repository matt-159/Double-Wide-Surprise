package com.github.matt159.dws.mixin.mixins.common.forestry.apiculture.gui;

import forestry.apiculture.gui.ContainerAlvearyHygroregulator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerAlvearyHygroregulator.class)
public abstract class ContainerAlvearyHygroregulatorMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 56),
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
