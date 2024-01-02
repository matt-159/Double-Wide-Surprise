package com.github.matt159.dws.mixin.mixins.client.thaumcraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.client.gui.GuiFocusPouch;

@Mixin(GuiFocusPouch.class)
public abstract class GuiFocusPouchMixin {
    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 240),
                    require = 1)
    private int modifyXTextureUVXOffset(int constant) {
        return 496;
    }
}
