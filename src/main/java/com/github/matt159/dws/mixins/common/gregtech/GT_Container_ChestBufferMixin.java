package com.github.matt159.dws.mixins.common.gregtech;

import gregtech.common.gui.GT_Container_ChestBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container_ChestBuffer.class)
public abstract class GT_Container_ChestBufferMixin {
    @ModifyConstant(method = "addSlots",
                    constant = {
                        @Constant(intValue = 8),
                        @Constant(intValue = 26),
                        @Constant(intValue = 44),
                        @Constant(intValue = 62)
                    },
                    remap = false,
                    require = 1)
    private int modifySlotXOffsets(int constant) {
        return constant + 81;
    }
}
