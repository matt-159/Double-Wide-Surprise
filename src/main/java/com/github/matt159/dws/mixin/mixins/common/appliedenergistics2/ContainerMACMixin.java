package com.github.matt159.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerMAC;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerMAC.class)
public abstract class ContainerMACMixin {
    @ModifyConstant(method = "setupConfig",
                    constant = { @Constant(intValue = 29),
                                 @Constant(intValue = 122),
                                 @Constant(intValue = 126) },
                    remap = false,
                    require = 3)
    private int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "setupConfig",
                    constant = @Constant(intValue = 187),
                    remap = false,
                    require = 1)
    private int modifyUpgradeSlotXOffset(int constant) {
        return constant + 162;
    }
}
