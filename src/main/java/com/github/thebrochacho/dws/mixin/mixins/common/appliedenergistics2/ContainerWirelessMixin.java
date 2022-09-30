package com.github.thebrochacho.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerWireless;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerWireless.class)
public abstract class ContainerWirelessMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 80),
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
