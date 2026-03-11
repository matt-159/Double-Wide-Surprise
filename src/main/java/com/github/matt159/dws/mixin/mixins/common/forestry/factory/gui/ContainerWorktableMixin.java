package com.github.matt159.dws.mixin.mixins.common.forestry.factory.gui;

import com.github.matt159.dws.util.Constants;
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
                    require = 0)
    private int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
