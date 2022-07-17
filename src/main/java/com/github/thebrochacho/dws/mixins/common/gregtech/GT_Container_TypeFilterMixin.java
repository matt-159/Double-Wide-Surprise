package com.github.thebrochacho.dws.mixins.common.gregtech;

import gregtech.common.gui.GT_Container_TypeFilter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container_TypeFilter.class)
public class GT_Container_TypeFilterMixin {
    @ModifyConstant(method = "addSlots",
                    constant = {
                        @Constant(intValue = 8, ordinal = 1),
                        @Constant(intValue = 26),
                        @Constant(intValue = 35),
                        @Constant(intValue = 44),
                        @Constant(intValue = 62),
                        @Constant(intValue = 80),
                        @Constant(intValue = 98),
                        @Constant(intValue = 116),
                        @Constant(intValue = 134)
                    },
                    remap = false,
                    require = 1)
    private int modifySlotXOffsets(int constant) {
        return constant + 81;
    }

}
