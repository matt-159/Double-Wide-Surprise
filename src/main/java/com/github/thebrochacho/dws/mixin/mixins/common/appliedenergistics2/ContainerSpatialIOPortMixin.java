package com.github.thebrochacho.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerSpatialIOPort;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerSpatialIOPort.class)
public abstract class ContainerSpatialIOPortMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 52),
                                 @Constant(intValue = 113) },
                    remap = false,
                    require = 2)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
