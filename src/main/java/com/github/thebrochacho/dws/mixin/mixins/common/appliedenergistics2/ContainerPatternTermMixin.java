package com.github.thebrochacho.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerPatternTerm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerPatternTerm.class)
public abstract class ContainerPatternTermMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 18,
                                           ordinal = 0),
                                 @Constant(intValue = 110),
                                 @Constant(intValue = 147) },
                    remap = false,
                    require = 5)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
