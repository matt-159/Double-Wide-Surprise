package com.github.thebrochacho.dws.mixin.mixins.common.forestry.energy;

import forestry.energy.gui.ContainerEnginePeat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerEnginePeat.class)
public abstract class ContainerEnginePeatMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 44),
                                 @Constant(intValue = 98),
                                 @Constant(intValue = 116) },
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
