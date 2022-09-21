package com.github.thebrochacho.dws.mixin.mixins.common.gregtech;

import gregtech.api.gui.GT_Container_3by3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container_3by3.class)
public abstract class GT_Container_3by3Mixin {
    @ModifyConstant(method = "addSlots",
                    constant = {
                        @Constant(intValue = 62),
                        @Constant(intValue = 80),
                        @Constant(intValue = 98)
                    },
                    remap = false,
                    require = 1)
    private int modifySlotXPos(int constant) {
        return constant + 81;
    }
}
