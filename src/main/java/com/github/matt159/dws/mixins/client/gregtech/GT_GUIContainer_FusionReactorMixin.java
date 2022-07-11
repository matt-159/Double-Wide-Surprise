package com.github.matt159.dws.mixins.client.gregtech;

import gregtech.common.gui.GT_GUIContainer_FusionReactor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_GUIContainer_FusionReactor.class)
public abstract class GT_GUIContainer_FusionReactorMixin {
    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = {
                        @Constant(intValue = -70),
                        @Constant(intValue = 8),
                        @Constant(intValue = 50)
                    },
                    remap = false,
                    require = 1)
    private int modifyDrawStringXOffset(int constant) {
        return constant + 85;
    }

    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 10),
                    remap = false,
                    require = 1)
    private int modifyDrawStringXOffset1(int constant) {
        return 116;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(   intValue = 5,
                                            ordinal = 0),
                    remap = false,
                    require = 1)
    private int modifyPowerBarXOffset(int constant) {
        return 96;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 147),
                    remap = false,
                    require = 1)
    private int modifyPowerBarMaxWidth(int constant) {
        return 145;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(   intValue = 5,
                                            ordinal = 1),
                    remap = false,
                    require = 1)
    private int modifyPowerBarHeight(int constant) {
        return 7;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(doubleValue = 148D),
                    remap = false,
                    require = 1)
    private double modifyPowerBarScale(double constant) {
        return 146D;
    }

    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 155),
                    remap = false,
                    require = 1)
    private int modifyPowerBarTextYOffset(int constant) {
        return constant - 2;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 156),
                    remap = false,
                    require = 1)
    private int modifyPowerBarYOffset(int constant) {
        return constant - 3;
    }
}
