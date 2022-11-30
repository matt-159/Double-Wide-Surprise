package com.github.thebrochacho.dws.mixin.mixins.common.forestry.factory.gui;

import forestry.factory.gui.ContainerWorktable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerWorktable.class)
public abstract class ContainerWorktableMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 8,
                                           ordinal = 1),
                                 @Constant(intValue = 11),
                                 @Constant(intValue = 77) },
                    remap = false,
                    require = 3)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
