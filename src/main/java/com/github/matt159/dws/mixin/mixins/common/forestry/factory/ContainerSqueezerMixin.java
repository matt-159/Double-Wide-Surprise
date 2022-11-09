package com.github.matt159.dws.mixin.mixins.common.forestry.factory;

import forestry.factory.gui.ContainerSqueezer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerSqueezer.class)
public abstract class ContainerSqueezerMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 17),
                                 @Constant(intValue = 97),
                                 @Constant(intValue = 147) },
                    remap = false,
                    require = 4)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
