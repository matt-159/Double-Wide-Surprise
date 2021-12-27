package com.github.thebrochacho.putin.mixins;

import net.minecraftforge.client.event.GuiScreenEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import travellersgear.client.ClientProxy;

@Mixin(ClientProxy.class)
public abstract class TGClientProxyMixin {
    @Inject(    method = "guiPostInit",
                at = @At("HEAD"),
                cancellable = true,
                remap = false,
                require = 1)
    private void onGuiPostInit(GuiScreenEvent.InitGuiEvent.Post event, CallbackInfo ci) {
        ci.cancel();
    }
}
