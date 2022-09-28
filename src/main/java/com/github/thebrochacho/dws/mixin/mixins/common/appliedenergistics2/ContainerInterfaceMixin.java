package com.github.thebrochacho.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerInterface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerInterface.class)
public abstract class ContainerInterfaceMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 8),
                    remap = false,
                    require = 3)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
