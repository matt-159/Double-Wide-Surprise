package com.github.thebrochacho.dws.mixins.client.gregtech;

import gregtech.api.gui.GT_GUIContainer_BasicTank;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_GUIContainer_BasicTank.class)
public abstract class GT_GUIContainer_BasicTankMixin {
    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = {
                        @Constant(intValue = 8),
                        @Constant(intValue = 10)
                    },
                    require = 1)
    private int modifyDrawStringXOffset(int constant) {
        return constant + 81;
    }
}
