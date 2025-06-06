package com.github.matt159.dws.mixin.mixins.client.minecraft;

import com.github.matt159.dws.util.TextureWhitelist;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(TextureManager.class)
public abstract class TextureManagerMixin {
    @Unique
    private ResourceLocation $DWS_resourceLocation;

    @Inject(method = "bindTexture",
            at = @At("HEAD"),
            require = 1)
    private void injectNewResourceLocation(ResourceLocation resourceLocation, CallbackInfo ci) {
        this.$DWS_resourceLocation = TextureWhitelist.checkResourceLocation(resourceLocation);
    }

    @Redirect(method = "bindTexture",
              at = @At(value = "INVOKE",
                       target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"),
              require = 1)
    private Object modifyMapGetArg(Map instance, Object o) {
        return instance.get($DWS_resourceLocation);
    }

    @ModifyArg(method = "bindTexture",
               at = @At(value = "INVOKE",
                        target = "Lnet/minecraft/client/renderer/texture/SimpleTexture;<init>(Lnet/minecraft/util/ResourceLocation;)V"),
               require = 1)
    private ResourceLocation modifySimpleTextureArg(ResourceLocation resourceLocation) {
        return this.$DWS_resourceLocation;
    }

    @Redirect(method = "bindTexture",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/client/renderer/texture/TextureManager;loadTexture(Lnet/minecraft/util/ResourceLocation;Lnet/minecraft/client/renderer/texture/ITextureObject;)Z"),
              require = 1)
    private boolean modifyLoadTextureArg(TextureManager instance,
                                         ResourceLocation resourceLocation,
                                         ITextureObject iTextureObject) {
        return instance.loadTexture(this.$DWS_resourceLocation, iTextureObject);
    }

    @Inject(method = "bindTexture",
            at = @At("RETURN"),
            require = 1)
    private void injectFreeResourceLocation(ResourceLocation resourceLocation, CallbackInfo ci) {
        $DWS_resourceLocation = null;
    }
}
