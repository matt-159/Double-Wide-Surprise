package com.github.thebrochacho.dws.mixins.common.gregtech;

import gregtech.api.gui.GT_Container_BasicTank;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container_BasicTank.class)
public abstract class GT_Container_BasicTankMixin {
    @ModifyConstant(method = "addSlots",
                    constant = {
                        @Constant(intValue = 59),
                        @Constant(intValue = 80)
                    },
                    remap = false,
                    require = 1)
    private int modifySlotXPos(int constant) {
        return constant + 81;
    }
}
