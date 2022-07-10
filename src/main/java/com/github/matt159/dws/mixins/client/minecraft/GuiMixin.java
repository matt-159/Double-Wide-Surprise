package com.github.matt159.dws.mixins.client.minecraft;

import com.github.matt159.dws.interfaces.minecraft.IGuiMixin;
import com.github.matt159.dws.util.TextureWhitelist;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin implements IGuiMixin {
    @Shadow(aliases = {"field_73735_i"})
    protected float zLevel;

    @Override
    public float getZLevel() {
        return zLevel;
    }

    @ModifyConstant(method = "drawTexturedModalRect",
                    constant = @Constant(floatValue = 0.00390625F),
                    require = 1)
    private float modifyUVConstant(float constant) {
        return TextureWhitelist.useOversizedTexture ? 0.001953125F : 0.00390625F;
    }
}
