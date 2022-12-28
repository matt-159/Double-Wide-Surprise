package com.github.matt159.dws.mixin.mixins.common.forestry.energy.gui;

import com.github.matt159.dws.util.Constants;
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
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
