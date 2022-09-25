package com.github.thebrochacho.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerIOPort;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerIOPort.class)
public abstract class ContainerIOPortMixin {
    @ModifyConstant(method = "setupConfig",
                    constant = {    @Constant(intValue = 19),
                                    @Constant(intValue = 122)   },
                    remap = false,
                    require = 2)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "setupConfig",
                    constant = @Constant(intValue = 187),
                    remap = false,
                    require = 3)
    private int modifyUpgradeSlotXOffset(int constant) {
        return constant + 162;
    }
}
