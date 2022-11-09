package com.github.matt159.dws.mixin.mixins.common.forestry.factory;

import forestry.factory.gui.ContainerFabricator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerFabricator.class)
public abstract class ContainerFabricatorMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 8,
                                           ordinal = 1),
                                 @Constant(intValue = 26),
                                 @Constant(intValue = 67),
                                 @Constant(intValue = 139) },
                    remap = false,
                    require = 5)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
