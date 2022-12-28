package com.github.matt159.dws.mixin.mixins.common.gregtech;

import com.github.matt159.dws.util.Constants;
import gregtech.common.gui.GT_Container_Filter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container_Filter.class)
public abstract class GT_Container_FilterMixin {
    @ModifyConstant(method = "addSlots",
                    constant = {
                        @Constant(intValue = 8),
                        @Constant(intValue = 18),
                        @Constant(intValue = 26),
                        @Constant(intValue = 35),
                        @Constant(intValue = 44),
                        @Constant(intValue = 52),
                        @Constant(intValue = 62),
                        @Constant(intValue = 80),
                        @Constant(intValue = 98),
                        @Constant(intValue = 116),
                        @Constant(intValue = 134)
                    },
                    remap = false,
                    require = 1)
    private int modifySlotXOffsets(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
