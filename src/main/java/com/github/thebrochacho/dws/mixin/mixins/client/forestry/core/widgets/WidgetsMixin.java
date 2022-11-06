package com.github.thebrochacho.dws.mixin.mixins.client.forestry.core.widgets;

import forestry.core.gui.widgets.GameTokenWidget;
import forestry.core.gui.widgets.ProbeButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = { GameTokenWidget.class,
                 ProbeButton.class })
public abstract class WidgetsMixin {
    @ModifyConstant(method = "draw",
                    constant = @Constant(intValue = 228),
                    remap = false,
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return 338;
    }
}
