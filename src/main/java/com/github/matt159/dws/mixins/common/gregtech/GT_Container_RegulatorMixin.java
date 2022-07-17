package com.github.matt159.dws.mixins.common.gregtech;

import gregtech.common.gui.GT_Container_Regulator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container_Regulator.class)
public abstract class GT_Container_RegulatorMixin {
    @ModifyConstant(method = "addSlots",
                    constant = {
                        //I have no idea why I had to start at ordinal 1.
                        //It should start at ordinal 0, but that didn't work correctly
                        @Constant(intValue = 8, ordinal = 1),
                        @Constant(intValue = 8, ordinal = 2),
                        @Constant(intValue = 8, ordinal = 3),
                        @Constant(intValue = 8, ordinal = 5),
                        @Constant(intValue = 26),
                        @Constant(intValue = 44),
                        @Constant(intValue = 64),
                        @Constant(intValue = 81),
                        @Constant(intValue = 98),
                        @Constant(intValue = 119),
                        @Constant(intValue = 136),
                        @Constant(intValue = 153)
                    },
                    remap = false,
                    require = 1)
    private int modifySlotXOffsets(int constant) {
        return constant + 81;
    }
}
