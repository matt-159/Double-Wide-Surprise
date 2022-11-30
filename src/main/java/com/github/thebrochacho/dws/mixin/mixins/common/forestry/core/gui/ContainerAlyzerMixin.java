package com.github.thebrochacho.dws.mixin.mixins.common.forestry.core.gui;

import forestry.core.gui.ContainerAlyzer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerAlyzer.class)
public abstract class ContainerAlyzerMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 223),
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 46;
    }
}
