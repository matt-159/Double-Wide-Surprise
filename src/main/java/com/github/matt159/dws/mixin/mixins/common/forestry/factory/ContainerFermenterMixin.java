package com.github.matt159.dws.mixin.mixins.common.forestry.factory;

import forestry.factory.gui.ContainerFermenter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerFermenter.class)
public abstract class ContainerFermenterMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 10),
                                 @Constant(intValue = 75),
                                 @Constant(intValue = 85),
                                 @Constant(intValue = 150) },
                    remap = false,
                    require = 5)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
