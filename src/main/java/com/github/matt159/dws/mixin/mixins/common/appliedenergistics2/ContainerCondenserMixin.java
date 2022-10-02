package com.github.matt159.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerCondenser;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerCondenser.class)
public abstract class ContainerCondenserMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 51),
                                 @Constant(intValue = 101),
                                 @Constant(intValue = 105) },
                    remap = false,
                    require = 3)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
