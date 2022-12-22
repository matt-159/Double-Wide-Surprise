package com.github.matt159.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerFormationPlane;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(ContainerFormationPlane.class)
public abstract class ContainerFormationPlaneMixin {

    @ModifyConstant(method = "setupConfig",
                    constant = @Constant(intValue = 8),
                    slice = @Slice(from = @At("HEAD"),
                                   to = @At(value = "INVOKE",
                                            target = "Lappeng/container/implementations/ContainerFormationPlane;getUpgradeable()Lappeng/api/implementations/IUpgradeableHost;",
                                            ordinal = 1)),
                    remap = false,
                    require = 1)
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
