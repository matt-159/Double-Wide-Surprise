package com.github.matt159.dws.mixin.mixins.client.minecraft;

import com.github.matt159.dws.util.TextureWhitelist;
import cpw.mods.fml.client.config.GuiUtils;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiUtils.class)
public abstract class GuiUtilsMixin {
    @Inject(method = "drawContinuousTexturedBox(Lnet/minecraft/util/ResourceLocation;IIIIIIIIIIIIF)V",
            at = @At("HEAD"),
            remap = false,
            require = 1)
    private static void injectStateEntry(ResourceLocation res, int x, int y, int u, int v, int width, int height, int textureWidth, int textureHeight, int topBorder, int bottomBorder, int leftBorder, int rightBorder, float zLevel, CallbackInfo ci) {
        TextureWhitelist.isBadContext = true;
    }

    @Inject(method = "drawContinuousTexturedBox(Lnet/minecraft/util/ResourceLocation;IIIIIIIIIIIIF)V",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private static void injectStateExit(ResourceLocation res, int x, int y, int u, int v, int width, int height, int textureWidth, int textureHeight, int topBorder, int bottomBorder, int leftBorder, int rightBorder, float zLevel, CallbackInfo ci) {
        TextureWhitelist.isBadContext = false;
    }
}
