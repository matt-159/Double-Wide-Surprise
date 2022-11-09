package com.github.matt159.dws.mixin.mixins.common.forestry.factory;

import forestry.factory.gui.ContainerRaintank;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerRaintank.class)
public abstract class ContainerRaintankMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 116),
                    remap = false,
                    require = 2)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
