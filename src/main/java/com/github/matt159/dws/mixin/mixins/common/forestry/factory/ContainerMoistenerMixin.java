package com.github.matt159.dws.mixin.mixins.common.forestry.factory;

import forestry.factory.gui.ContainerMoistener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerMoistener.class)
public abstract class ContainerMoistenerMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 39),
                                 @Constant(intValue = 105),
                                 @Constant(intValue = 143) },
                    remap = false,
                    require = 5)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
