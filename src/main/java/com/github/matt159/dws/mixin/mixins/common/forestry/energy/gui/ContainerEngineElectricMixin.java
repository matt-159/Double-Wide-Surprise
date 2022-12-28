package com.github.matt159.dws.mixin.mixins.common.forestry.energy.gui;

import com.github.matt159.dws.util.Constants;
import forestry.energy.gui.ContainerEngineElectric;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerEngineElectric.class)
public abstract class ContainerEngineElectricMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 84,
                                         ordinal = 1),
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
