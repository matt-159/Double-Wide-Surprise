package com.github.thebrochacho.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerQuartzKnife;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerQuartzKnife.class)
public abstract class ContainerQuartzKnifeMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 94),
                                 @Constant(intValue = 134) },
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
