package com.github.matt159.dws.mixin.mixins.common.mariculture;

import com.github.matt159.dws.util.Constants;
import mariculture.core.gui.ContainerCrucible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerCrucible.class)
public abstract class ContainerCrucibleMixin {
    @ModifyConstant(method = "<init>",
                    constant = {
                        @Constant(intValue = 29),
                        @Constant(intValue = 38),
                        @Constant(intValue = 145)
                    },
                    require = 5)
    private int modifySlotsXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 27),
                    require = 1)
    private int modifyHotbarStart(int constant) {
        return 54;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private int modifyHotbarEnd(int constant) {
        return 18;
    }
}
