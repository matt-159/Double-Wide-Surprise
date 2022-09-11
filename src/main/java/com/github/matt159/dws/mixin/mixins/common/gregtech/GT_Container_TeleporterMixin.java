package com.github.matt159.dws.mixin.mixins.common.gregtech;

import gregtech.common.gui.GT_Container_Teleporter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container_Teleporter.class)
public abstract class GT_Container_TeleporterMixin {
    @ModifyConstant(method = "addSlots",
                    constant = {
                        @Constant(intValue = 8),
                        @Constant(intValue = 26),
                        @Constant(intValue = 134),
                        @Constant(intValue = 152)
                    },
                    remap = false,
                    require = 1)
    private int modifySlotXOffsets(int constant) {
        return constant + 81;
    }
}
