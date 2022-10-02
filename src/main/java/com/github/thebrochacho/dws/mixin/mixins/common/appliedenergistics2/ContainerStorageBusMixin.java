package com.github.thebrochacho.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerStorageBus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerStorageBus.class)
public abstract class ContainerStorageBusMixin {
    @ModifyConstant(method = "setupConfig",
                    constant = @Constant(intValue = 8,
                                         ordinal = 1),
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "setupConfig",
                    constant = @Constant(intValue = 187),
                    remap = false,
                    require = 5)
    private int modifyUpgradeSlotXOffset(int constant) {
        return constant + 162;
    }
}
