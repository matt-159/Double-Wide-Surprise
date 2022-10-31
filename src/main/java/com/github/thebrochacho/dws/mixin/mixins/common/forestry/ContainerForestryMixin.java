package com.github.thebrochacho.dws.mixin.mixins.common.forestry;

import forestry.core.gui.ContainerForestry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerForestry.class)
public abstract class ContainerForestryMixin {
    @ModifyConstant(method = "addPlayerInventory",
                    constant = @Constant(intValue = 9),
                    remap = false,
                    require = 1)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
