package com.github.thebrochacho.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerCraftingTerm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerCraftingTerm.class)
public abstract class ContainerCraftingTermMixin {

    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 37),
                                 @Constant(intValue = 131) },
                    remap = false,
                    require = 2)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
