package com.github.matt159.dws.mixin.mixins.client.forestry.core;

import forestry.core.gui.GuiEscritoire;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiEscritoire.class)
public abstract class GuiEscritoireMixin {
    @ModifyConstant(method = { "<init>",
                               "drawGuiContainerBackgroundLayer" },
                    constant = { @Constant(intValue = 14,
                                           ordinal = 0),
                                 @Constant(intValue = 52),
                                 @Constant(intValue = 73),
                                 @Constant(intValue = 94),
                                 @Constant(intValue = 115),
                                 @Constant(intValue = 136),
                                 @Constant(intValue = 170) },
                    remap = false,
                    require = 1)
    private int modifyXOffsets(int constant) {
        return constant + 64;
    }
}
