package com.github.matt159.dws.mixin.mixins.client.gregtech;

import gregtech.common.gui.GT_GUIContainer_MicrowaveEnergyTransmitter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_GUIContainer_MicrowaveEnergyTransmitter.class)
public abstract class GT_GUIContainer_MicrowaveEnergyTransmitterMixin {
    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 46),
                    require = 1)
    private int modifyDrawStringXOffset(int constant) {
        return constant + 81;
    }
}
