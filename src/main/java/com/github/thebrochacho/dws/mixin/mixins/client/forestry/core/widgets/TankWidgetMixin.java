package com.github.thebrochacho.dws.mixin.mixins.client.forestry.core.widgets;

import forestry.core.gui.widgets.TankWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TankWidget.class)
public abstract class TankWidgetMixin {
    @Shadow(remap = false)
    private int overlayTexX;

    @Inject(method = "<init>",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private void injectUpdatedOverlayTexX(CallbackInfo ci) {
        overlayTexX = 338;
    }
}
