package com.github.thebrochacho.dws.mixin.mixins.common.gregtech;

import gregtech.api.gui.GT_Container_4by4;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container_4by4.class)
public abstract class GT_Container_4by4Mixin {
    @ModifyConstant(method = "addSlots",
                    constant = {
                        @Constant(intValue = 53),
                        @Constant(intValue = 71),
                        @Constant(intValue = 89),
                        @Constant(intValue = 107)
                    },
                    remap = false,
                    require = 1)
    private int modifySlotXPos(int constant) {
        return constant + 81;
    }
}
