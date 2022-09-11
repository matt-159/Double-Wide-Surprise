package com.github.matt159.dws.mixin.mixins.client.travellersgear;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import travellersgear.client.gui.GuiButtonGear;

@Mixin(GuiButtonGear.class)
public abstract class GuiButtonGearMixin {
    @Inject(method = "<init>",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    protected void disableButton(int id, int x, int y, CallbackInfo ci) {
        ((GuiButtonGear) (Object) (this)).enabled = false;
    }

    @Inject(method = "drawButton",
            at = @At("HEAD"),
            remap = false,
            cancellable = true,
            require = 1)
    protected void dontDrawButton(Minecraft mc, int x, int y, CallbackInfo ci) {
        ci.cancel();
    }
}
