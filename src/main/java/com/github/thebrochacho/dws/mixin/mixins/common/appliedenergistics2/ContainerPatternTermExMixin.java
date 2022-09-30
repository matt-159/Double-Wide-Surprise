package com.github.thebrochacho.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerPatternTermEx;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerPatternTermEx.class)
public abstract class ContainerPatternTermExMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 15),
                                 @Constant(intValue = 112),
                                 @Constant(intValue = 147) },
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
