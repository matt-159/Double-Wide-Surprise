package com.github.matt159.dws.mixin.mixins.client.mariculture;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.util.Constants;
import mariculture.core.gui.GuiCrucible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiCrucible.class)
public abstract class GuiCrucibleMixin implements IDWSGui {
    @ModifyConstant(method = "<init>",
                    constant = {
                        @Constant(intValue = 98),
                        @Constant(intValue = 65)
                    },
                    require = 2)
    private int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "addToolTip",
                    constant = {
                        @Constant(intValue = 12),
                        @Constant(intValue = 16)
                    },
                    require = 2,
                    remap = false)
    private int modifyTooltipXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawBackground",
                    constant = {
                        @Constant(intValue = 12, ordinal = 0),
                        @Constant(intValue = 38)
                    },
                    require = 2,
                    remap = false)
    private int modifyOverlayXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawBackground",
                    constant = {
                        @Constant(intValue = 251),
                        @Constant(intValue = 242)
                    },
                    require = 2,
                    remap = false)
    private int modifyOverlayTextureUVXOffset(int constant) {
        return constant + 162;
    }
}
