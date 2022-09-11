package com.github.thebrochacho.dws.mixin.mixins.common.gregtech;

import gregtech.common.gui.GT_Container_OutputHatch;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container_OutputHatch.class)
public abstract class GT_Container_OutputHatchMixin {
    @ModifyConstant(method = "addSlots",
                    constant = {
                        @Constant(intValue = 59),
                        @Constant(intValue = 80),
                        @Constant(intValue = 150)
                    },
                    remap = false,
                    require = 1)
    private int modifySlotXOffsets(int constant) {
        return constant + 81;
    }
}