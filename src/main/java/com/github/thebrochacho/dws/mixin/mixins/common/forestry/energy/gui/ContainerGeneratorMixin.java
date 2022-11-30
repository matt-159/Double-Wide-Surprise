package com.github.thebrochacho.dws.mixin.mixins.common.forestry.energy.gui;

import forestry.energy.gui.ContainerGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerGenerator.class)
public abstract class ContainerGeneratorMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 22),
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
