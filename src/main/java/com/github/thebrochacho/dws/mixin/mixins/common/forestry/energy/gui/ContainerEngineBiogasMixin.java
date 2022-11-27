package com.github.thebrochacho.dws.mixin.mixins.common.forestry.energy.gui;

import forestry.energy.gui.ContainerEngineBiogas;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerEngineBiogas.class)
public abstract class ContainerEngineBiogasMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 143),
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}