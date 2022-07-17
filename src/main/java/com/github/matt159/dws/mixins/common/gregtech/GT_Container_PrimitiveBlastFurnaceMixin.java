package com.github.matt159.dws.mixins.common.gregtech;

import gregtech.common.gui.GT_Container_PrimitiveBlastFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container_PrimitiveBlastFurnace.class)
public abstract class GT_Container_PrimitiveBlastFurnaceMixin {
    @ModifyConstant(method = "addSlots",
                    constant = {
                        @Constant(intValue = 34),
                        @Constant(intValue = 86)
                    },
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
