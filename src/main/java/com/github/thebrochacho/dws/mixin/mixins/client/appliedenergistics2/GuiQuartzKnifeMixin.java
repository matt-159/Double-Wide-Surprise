package com.github.thebrochacho.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiQuartzKnife;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiQuartzKnife.class)
public abstract class GuiQuartzKnifeMixin {
    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 24),
                    require = 1)
    private int modifyTextboxXOffset(int constant) {
        return constant + 81;
    }
}
