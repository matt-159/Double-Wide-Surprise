package com.github.matt159.dws.mixin.mixins.common.forestry.core.gui;

import com.github.matt159.dws.util.Constants;
import forestry.core.gui.ContainerAnalyzer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerAnalyzer.class)
public abstract class ContainerAnalyzerMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 8,
                                           ordinal = 1),
                                 @Constant(intValue = 73),
                                 @Constant(intValue = 134),
                                 @Constant(intValue = 143) },
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
