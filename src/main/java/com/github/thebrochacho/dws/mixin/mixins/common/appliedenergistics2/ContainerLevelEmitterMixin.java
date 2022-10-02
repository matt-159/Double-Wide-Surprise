package com.github.thebrochacho.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerLevelEmitter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerLevelEmitter.class)
public abstract class ContainerLevelEmitterMixin {
    @ModifyConstant(method = "setupConfig",
                    constant = @Constant(intValue = 187),
                    remap = false,
                    require = 1)
    private int modifyUpgradeSlotXOffset(int constant) {
        return constant + 162;
    }

    @ModifyConstant(method = "setupConfig",
                    constant = @Constant(intValue = 124),
                    remap = false,
                    require = 1)
    private int modifyFakeSlotXOffset(int constant) {
        return constant + 81;
    }
}
