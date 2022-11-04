package com.github.thebrochacho.dws.mixin.mixins.common.forestry.core;

import forestry.core.gui.ContainerEscritoire;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerEscritoire.class)
public abstract class ContainerEscritoireMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 17),
                                 @Constant(intValue = 97),
                                 @Constant(intValue = 177) },
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 64;
    }
}
