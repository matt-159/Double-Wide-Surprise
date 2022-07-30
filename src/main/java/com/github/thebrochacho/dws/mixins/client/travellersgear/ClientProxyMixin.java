package com.github.thebrochacho.dws.mixins.client.travellersgear;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import travellersgear.client.ClientProxy;

@Mixin(ClientProxy.class)
public abstract class ClientProxyMixin {

    @ModifyConstant(method = "guiPostInit",
                    constant = @Constant(intValue = 176),
                    remap = false,
                    require = 1)
    private int modifyXSize(int constant) {
        return 338;
    }

    // Lmao. Budget ASM
    @Redirect(  method = "guiPostInit",
                at = @At(   value = "INVOKE",
                            target = "Ltravellersgear/common/util/ModCompatability;isNeiHidden()Z"),
                remap = false,
                require = 1)
    private boolean redirectIsHidden() {
        return false;
    }
}
