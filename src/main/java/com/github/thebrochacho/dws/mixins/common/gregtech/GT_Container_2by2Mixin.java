package com.github.thebrochacho.dws.mixins.common.gregtech;

import gregtech.api.gui.GT_Container_2by2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container_2by2.class)
public abstract class GT_Container_2by2Mixin {
    @ModifyConstant(method = "addSlots",
                    constant = {
                        @Constant(intValue = 71),
                        @Constant(intValue = 89)
                    },
                    remap = false,
                    require = 1)
    private int modifySlotXPos(int constant) {
        return constant + 81;
    }
}
