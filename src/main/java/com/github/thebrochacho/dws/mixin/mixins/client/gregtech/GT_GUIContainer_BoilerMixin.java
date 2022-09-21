package com.github.thebrochacho.dws.mixin.mixins.client.gregtech;

import gregtech.common.gui.GT_GUIContainer_Boiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_GUIContainer_Boiler.class)
public abstract class GT_GUIContainer_BoilerMixin {
    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = {
                        @Constant(intValue = 70),
                        @Constant(intValue = 83),
                        @Constant(intValue = 96),
                        @Constant(intValue = 117)
                    },
                    require = 1)
    private int modifyXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = {
                        @Constant(intValue = 177),
                        @Constant(intValue = 194),
                        @Constant(intValue = 204),
                        @Constant(intValue = 214)
                    },
                    require = 1)
    private int modifyTextureXOffset(int constant) {
        return constant + 162;
    }
}