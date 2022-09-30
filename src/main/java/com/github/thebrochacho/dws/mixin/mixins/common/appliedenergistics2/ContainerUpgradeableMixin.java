package com.github.thebrochacho.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerUpgradeable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerUpgradeable.class)
public abstract class ContainerUpgradeableMixin {

    @ModifyConstant(method = "setupConfig",
                    constant = @Constant(intValue = 80),
                    remap = false,
                    require = 10)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = { "<init>",
                               "setupUpgrades" },
                    constant = { @Constant(intValue = 186),
                                 @Constant(intValue = 187) },
                    remap = false,
                    require = 1)
    private int modifyUpgradeSlotXOffset(int constant) {
        return constant + 162;
    }
}
