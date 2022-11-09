package com.github.matt159.dws.mixin.mixins.common.forestry.factory;

import forestry.factory.gui.ContainerBottler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerBottler.class)
public abstract class ContainerBottlerMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 26),
                                 @Constant(intValue = 116) },
                    remap = false,
                    require = 3)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
