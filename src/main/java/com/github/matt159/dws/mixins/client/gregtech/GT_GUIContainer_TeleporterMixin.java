package com.github.matt159.dws.mixins.client.gregtech;

import gregtech.common.gui.GT_GUIContainer_Teleporter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_GUIContainer_Teleporter.class)
public abstract class GT_GUIContainer_TeleporterMixin {
    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 46),
                    remap = false,
                    require = 1)
    private int modifyDrawStringXOffset(int constant) {
        return constant + 81;
    }
}