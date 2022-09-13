package com.github.thebrochacho.dws.mixin.mixins.client.minecraft.renderer;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderItem.class)
public abstract class RenderItemMixin {
    @Inject(method = "renderItemAndEffectIntoGUI",
            at = @At("HEAD"),
            cancellable = true,
            require = 1)
    private void injectEarlyReturn(FontRenderer p_82406_1_, TextureManager p_82406_2_, ItemStack p_82406_3_, int p_82406_4_, int p_82406_5_, CallbackInfo ci) {
        if (p_82406_3_ != null && p_82406_3_.stackSize == 0) {
            ci.cancel();
        }
    }

    @Inject(method = "renderItemOverlayIntoGUI(Lnet/minecraft/client/gui/FontRenderer;Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V",
            at = @At("HEAD"),
            cancellable = true,
            require = 1)
    private void injectEarlyReturn_(FontRenderer p_94148_1_, TextureManager p_94148_2_, ItemStack p_94148_3_, int p_94148_4_, int p_94148_5_, String p_94148_6_, CallbackInfo ci) {
        if (p_94148_3_ != null && p_94148_3_.stackSize == 0) {
            ci.cancel();
        }
    }

}
