package com.github.thebrochacho.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.widgets.GuiScrollbar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiScrollbar.class)
public abstract class GuiScrollbarMixin {
    @ModifyConstant(method = "draw",
                    constant = @Constant(intValue = 232),
                    remap = false,
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return 372;
    }
}
