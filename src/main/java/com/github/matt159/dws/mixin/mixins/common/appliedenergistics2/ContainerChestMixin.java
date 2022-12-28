package com.github.matt159.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerChest;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerChest.class)
public abstract class ContainerChestMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 80),
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
