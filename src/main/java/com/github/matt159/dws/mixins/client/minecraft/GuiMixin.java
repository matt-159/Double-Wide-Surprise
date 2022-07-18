package com.github.matt159.dws.mixins.client.minecraft;

import com.github.matt159.dws.util.TextureWhitelist;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Gui.class)
public abstract class GuiMixin {
    @ModifyConstant(method = "drawTexturedModalRect",
                    constant = @Constant(floatValue = 0.00390625F),
                    require = 1)
    private float modifyUVConstant(float constant) {
        return TextureWhitelist.useOversizedTexture ? 0.001953125F : 0.00390625F;
    }
}
