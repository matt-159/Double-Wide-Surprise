package com.github.thebrochacho.putin.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import travellersgear.client.gui.GuiButtonGear;

@Mixin(GuiButtonGear.class)
public abstract class TGButtonGearMixin {
    @Inject(method = "drawButton",
            at = @At("HEAD"),
            remap = false,
            cancellable = true,
            require = 1)
    protected void dontDrawButton(Minecraft mc, int x, int y, CallbackInfo ci) {
        ci.cancel();
    }
}
