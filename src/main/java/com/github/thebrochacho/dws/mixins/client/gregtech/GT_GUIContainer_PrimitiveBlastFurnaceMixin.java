package com.github.thebrochacho.dws.mixins.client.gregtech;

import gregtech.common.gui.GT_GUIContainer_PrimitiveBlastFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_GUIContainer_PrimitiveBlastFurnace.class)
public abstract class GT_GUIContainer_PrimitiveBlastFurnaceMixin {
    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 58),
                    require = 1)
    private int modifyTextureXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return 338;
    }
}
