package com.github.thebrochacho.dws.mixins.client.nei;

import codechicken.nei.ItemPanel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ItemPanel.class)
public abstract class ItemPanelMixin {
    @ModifyConstant(method = "getMarginLeft",
                    constant = @Constant(intValue = 176),
                    require = 1,
                    remap = false)
    private int modifyGuiWidth(int constant) {
        return 338;
    }
}