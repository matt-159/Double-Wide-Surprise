package com.github.matt159.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerInscriber;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerInscriber.class)
public abstract class ContainerInscriberMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 45),
                                 @Constant(intValue = 63),
                                 @Constant(intValue = 113) },
                    remap = false,
                    require = 4)
    private int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
