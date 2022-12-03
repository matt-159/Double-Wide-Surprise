package com.github.matt159.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerQNB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerQNB.class)
public abstract class ContainerQNBMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 80),
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
