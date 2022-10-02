package com.github.matt159.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerGrinder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerGrinder.class)
public abstract class ContainerGrinderMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 12),
                                 @Constant(intValue = 12 + 18),
                                 @Constant(intValue = 12 + 36),
                                 @Constant(intValue = 80),
                                 @Constant(intValue = 112),
                                 @Constant(intValue = 112 + 18),
                                 @Constant(intValue = 112 + 36) },
                    remap = false,
                    require = 7)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
