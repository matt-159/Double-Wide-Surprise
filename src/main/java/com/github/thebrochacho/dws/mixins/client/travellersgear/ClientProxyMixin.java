package com.github.thebrochacho.dws.mixins.client.travellersgear;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import travellersgear.client.ClientProxy;

@Mixin(ClientProxy.class)
public class ClientProxyMixin {

    @ModifyConstant(method = "guiPostInit",
                    constant = @Constant(intValue = 176),
                    remap = false,
                    require = 1)
    private int modifyXSize(int constant) {
        return 338;
    }
}
