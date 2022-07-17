package com.github.thebrochacho.dws.mixins.client.gregtech;

import gregtech.common.gui.GT_GUIContainer_QuantumChest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_GUIContainer_QuantumChest.class)
public abstract class GT_GUIContainer_QuantumChestMixin {
    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 10),
                    remap = false,
                    require = 1)
    private int modifyDrawStringXOffset(int constant) {
        return constant + 81;
    }
}
