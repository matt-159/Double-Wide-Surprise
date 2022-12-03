package com.github.matt159.dws.mixin.mixins.client.nei;

import codechicken.nei.NEIClientConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(NEIClientConfig.class)
public abstract class NEIClientConfigMixin {
    @ModifyConstant(method = "onWorldLoad",
                    constant = @Constant(intValue = 54),
                    remap = false,
                    require = 1)
    private static int modifyCreativeInvSize(int constant) {
        return 108;
    }
}
