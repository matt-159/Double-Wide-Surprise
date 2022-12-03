package com.github.matt159.dws.mixin.mixins.client.minecraft.gui;

import com.github.matt159.dws.util.TextureWhitelist;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Gui.class)
public abstract class GuiMixin {
    private static final float fraction256th = 0.00390625F;
    private static final float fraction512th = 0.001953125F;

    @ModifyConstant(method = "drawTexturedModalRect",
                    constant = @Constant(floatValue = 0.00390625F),
                    require = 1)
    private float modifyUVConstant(float constant) {
        return TextureWhitelist.useDoubleWideTexture ? fraction512th : fraction256th;
    }
}
