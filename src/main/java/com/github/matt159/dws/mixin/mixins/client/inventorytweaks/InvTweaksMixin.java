package com.github.matt159.dws.mixin.mixins.client.inventorytweaks;

import invtweaks.InvTweaks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(InvTweaks.class)
public abstract class InvTweaksMixin {
    @ModifyConstant(method = "*",
                    constant = { @Constant(intValue = 9),
                                 @Constant(intValue = 27),
                                 @Constant(intValue = 36) },
                    remap = false,
                    require = 8)
    private int modifyInventorySize(int constant) {
        return constant * 2;
    }
}
