package com.github.matt159.dws.mixin.mixins.common.forestry.factory.gui;

import com.github.matt159.dws.util.Constants;
import forestry.factory.gui.ContainerStill;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerStill.class)
public abstract class ContainerStillMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 10),
                                 @Constant(intValue = 150) },
                    remap = false,
                    require = 3)
    private int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
