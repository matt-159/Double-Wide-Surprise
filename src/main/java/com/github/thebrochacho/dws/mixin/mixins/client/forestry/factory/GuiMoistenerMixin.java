package com.github.thebrochacho.dws.mixin.mixins.client.forestry.factory;

import forestry.factory.gui.GuiMoistener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiMoistener.class)
public abstract class GuiMoistenerMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 16,
                                         ordinal = 0),
                    require = 1)
    private int modifyXOffsetInit(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = { @Constant(intValue = 93),
                                 @Constant(intValue = 124) },
                    require = 2)
    private int modifyXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
            constant = @Constant(intValue = 176),
            require = 2)
    private int modifyTextureUVXOffset(int constant) {
        return 338;
    }
}
