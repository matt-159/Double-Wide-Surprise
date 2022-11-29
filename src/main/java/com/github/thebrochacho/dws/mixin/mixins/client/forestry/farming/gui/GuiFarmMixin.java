package com.github.thebrochacho.dws.mixin.mixins.client.forestry.farming.gui;

import forestry.farming.gui.GuiFarm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiFarm.class)
public abstract class GuiFarmMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 15),
                                 @Constant(intValue = 51),
                                 @Constant(intValue = 69),
                                 @Constant(intValue = 87) },
                    require = 6)
    private int modifySlotXOffset(int constant) {
        return constant + 61;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 81),
                    require = 1)
    private int modifyTextureXOffset(int constant) {
        return constant + 61;
    }
}
