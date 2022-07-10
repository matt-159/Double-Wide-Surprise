package com.github.matt159.dws.mixins.client.gregtech;

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
                        @Constant(intValue = 42)
                    },
                    remap = false,
                    require = 1)
    private int modifyXOffset(int constant) {
        return constant + 81;
    }
}
