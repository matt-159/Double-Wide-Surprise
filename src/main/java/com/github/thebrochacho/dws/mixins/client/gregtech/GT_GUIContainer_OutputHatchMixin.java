package com.github.thebrochacho.dws.mixins.client.gregtech;

import gregtech.common.gui.GT_GUIContainer_OutputHatch;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_GUIContainer_OutputHatch.class)
public abstract class GT_GUIContainer_OutputHatchMixin {
    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = {
                        @Constant(intValue = 10),
                        @Constant(intValue = 101)
                    },
                    remap = false,
                    require = 1)
    private int modifyDrawStringXOffset(int constant) {
        return constant + 81;
    }
}
