package com.github.thebrochacho.dws.mixins.client.minecraft;

import com.github.thebrochacho.dws.util.TextureWhitelist;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(TextureManager.class)
public abstract class TextureManagerMixin {
    @ModifyVariable(method = "bindTexture",
                    at = @At(value = "HEAD"),
                    argsOnly = true,
                    index = 1,
                    require = 1)
    private ResourceLocation modifyResourceLocation(ResourceLocation value) {
        return TextureWhitelist.checkResourceLocation(value);
    }
}
