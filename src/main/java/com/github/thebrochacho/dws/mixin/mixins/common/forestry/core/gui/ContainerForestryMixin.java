package com.github.thebrochacho.dws.mixin.mixins.common.forestry.core.gui;

import forestry.core.gui.ContainerForestry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ContainerForestry.class)
public abstract class ContainerForestryMixin {
    @ModifyConstant(method = "addPlayerInventory",
                    constant = @Constant(intValue = 9),
                    remap = false,
                    require = 1)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }

    @ModifyVariable(method = "addPlayerInventory",
                    at = @At("HEAD"),
                    argsOnly = true,
                    ordinal = 0,
                    remap = false,
                    require = 1)
    private int modifyPlayerXOffset(int constant) {
        return 8;
    }
}
