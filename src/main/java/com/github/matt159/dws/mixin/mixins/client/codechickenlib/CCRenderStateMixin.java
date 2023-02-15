package com.github.matt159.dws.mixin.mixins.client.codechickenlib;

import codechicken.lib.render.CCRenderState;
import com.github.matt159.dws.util.TextureWhitelist;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CCRenderState.class)
public abstract class CCRenderStateMixin {
    @Inject(method = "changeTexture(Lnet/minecraft/util/ResourceLocation;)V",
            at = @At("HEAD"),
            remap = false,
            require = 1)
    private static void injectStateEntry(ResourceLocation texture, CallbackInfo ci) {
        if (texture.toString().equals("nei:textures/gui/inv.png")) return;

        TextureWhitelist.isNEIContext = true;
    }

    @Inject(method = "changeTexture(Lnet/minecraft/util/ResourceLocation;)V",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private static void injectStateExit(ResourceLocation texture, CallbackInfo ci) {
        TextureWhitelist.isNEIContext = false;
    }
}
