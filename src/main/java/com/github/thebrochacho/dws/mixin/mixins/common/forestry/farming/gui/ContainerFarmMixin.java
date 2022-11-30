package com.github.thebrochacho.dws.mixin.mixins.common.forestry.farming.gui;

import forestry.farming.gui.ContainerFarm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerFarm.class)
public abstract class ContainerFarmMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 15),
                                 @Constant(intValue = 63),
                                 @Constant(intValue = 123),
                                 @Constant(intValue = 164) },
                    require = 5)
    private int modifySlotXOffset(int constant) {
        return constant + 61;
    }
}
