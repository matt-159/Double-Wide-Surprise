package com.github.matt159.dws.mixin.mixins.client.minecraft.gui;

import net.minecraftforge.client.GuiIngameForge;

import com.github.matt159.dws.Tags;
import com.github.matt159.dws.util.TextureWhitelist;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

@Mixin(GuiIngameForge.class)
public abstract class GuiIngameForgeMixin {
    @Unique
    private static final ResourceLocation $dws_WIDGETS_TEXTURE = new ResourceLocation(Tags.MOD_ID, "textures/gui/widgets.png");

    @Redirect(method = "renderHotbar",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V"),
              require = 1)
    private void redirectTextureBind(TextureManager instance, ResourceLocation resourceLocation) {
        instance.bindTexture($dws_WIDGETS_TEXTURE);
        TextureWhitelist.useDoubleWideTexture = true;
    }

    @ModifyConstant(method = "renderHotbar",
                    constant = @Constant(intValue = 9),
                    remap = false,
                    require = 1)
    private int modifyHotbarSize(int constant) {
        return 18;
    }

    @ModifyConstant(method = "renderHotbar",
                    constant = {    @Constant(intValue = 90),
                                    @Constant(intValue = 91)    },
                    remap = false,
                    require = 1)
    private int modifyHotbarXOffset(int constant) {
        return constant + 90;
    }

    @ModifyConstant(method = "renderHotbar",
                    constant = @Constant(intValue = 182),
                    remap = false,
                    require = 1)
    private int modifyHotbarTextureWidth(int constant) {
        return 362;
    }
}
