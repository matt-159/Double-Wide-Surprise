package com.github.matt159.dws.mixin.mixins.client.nei;

import codechicken.nei.recipe.DefaultOverlayHandler;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultOverlayHandler.class)
public abstract class DefaultOverlayHandlerMixin {
    @Shadow(remap = false)
    public int offsetx;

    @Inject(method = "<init>(II)V",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private void modifyXOffset(CallbackInfo ci) {
        this.offsetx += Constants.GENERAL_X_OFFSET;
    }
}
