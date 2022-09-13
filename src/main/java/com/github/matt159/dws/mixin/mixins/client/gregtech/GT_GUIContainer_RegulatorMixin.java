package com.github.matt159.dws.mixin.mixins.client.gregtech;

import gregtech.common.gui.GT_GUIContainer_Regulator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_GUIContainer_Regulator.class)
public abstract class GT_GUIContainer_RegulatorMixin {
    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = {
                        @Constant(intValue = 120),
                        @Constant(intValue = 137),
                        @Constant(intValue = 155)
                    },
                    require = 1)
    private int modifyDrawStringXOffset(int constant) {
        return constant + 81;
    }
}
