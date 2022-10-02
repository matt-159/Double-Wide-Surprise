package com.github.thebrochacho.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.AEBaseContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AEBaseContainer.class)
public abstract class AEBaseContainerMixin {
    @ModifyConstant(method = "bindPlayerInventory",
                    constant = @Constant(intValue = 9),
                    remap = false,
                    require = 8)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
