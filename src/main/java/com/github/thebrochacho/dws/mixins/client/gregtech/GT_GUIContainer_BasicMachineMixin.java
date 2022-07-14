package com.github.thebrochacho.dws.mixins.client.gregtech;

import gregtech.api.gui.GT_GUIContainer_BasicMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_GUIContainer_BasicMachine.class)
public abstract class GT_GUIContainer_BasicMachineMixin {
    @ModifyConstant(method = "drawTooltip",
                    constant = {
                        @Constant(intValue = 7),
                        @Constant(intValue = 24),
                        @Constant(intValue = 25),
                        @Constant(intValue = 42),
                    },
                    remap = false,
                    require = 1)
    private int modifyXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = {
                        @Constant(intValue = 7),
                        @Constant(intValue = 25),
                        @Constant(intValue = 78),
                        @Constant(intValue = 79)
                    },
                    require = 1)
    private int modifyProgressBarXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifyUVXOffset(int constant) {
        return 338;
    }
}
