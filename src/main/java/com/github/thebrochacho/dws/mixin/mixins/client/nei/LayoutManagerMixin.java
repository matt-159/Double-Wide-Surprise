package com.github.thebrochacho.dws.mixin.mixins.client.nei;

import codechicken.nei.LayoutManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LayoutManager.class)
public abstract class LayoutManagerMixin {
    @ModifyConstant(method = "layout",
                    constant = {
                        @Constant(intValue = 40),
                        @Constant(intValue = 76)
                    },
                    remap = false,
                    require = 1)
    private static int modifyThresholds(int constant) {
        return Integer.MIN_VALUE;
    }
}
