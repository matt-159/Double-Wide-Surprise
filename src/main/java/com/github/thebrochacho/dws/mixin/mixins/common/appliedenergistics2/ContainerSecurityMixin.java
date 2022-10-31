package com.github.thebrochacho.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerSecurity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerSecurity.class)
public abstract class ContainerSecurityMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 37),
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 212),
                    remap = false,
                    require = 2)
    private int modifyRightSlotsXOffset(int constant) {
        return constant + 143;
    }
}