package com.github.thebrochacho.dws.mixin.mixins.common.gregtech;

import gregtech.common.gui.GT_Container_ItemDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container_ItemDistributor.class)
public abstract class GT_Container_ItemDistributorMixin {
    @ModifyConstant(method = "addSlots",
            constant = {
                    @Constant(intValue = 8),
                    @Constant(intValue = 26),
                    @Constant(intValue = 44),
            },
            remap = false,
            require = 1)
    private int modifySlotXOffsets(int constant) {
        return constant + 81;
    }
}
