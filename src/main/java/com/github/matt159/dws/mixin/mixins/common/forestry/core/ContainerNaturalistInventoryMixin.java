package com.github.matt159.dws.mixin.mixins.common.forestry.core;

import forestry.core.gui.ContainerNaturalistInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerNaturalistInventory.class)
public abstract class ContainerNaturalistInventoryMixin {
    @ModifyConstant(method = "addInventory",
                    constant = @Constant(intValue = 100),
                    remap = false,
                    require = 1)
    private static int modifySlotXOffset(int constant) {
        return constant + 71;
    }
}
