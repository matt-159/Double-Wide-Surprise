package com.github.matt159.dws.mixin.mixins.common.railcraft;

import com.github.matt159.dws.interfaces.IDWSContainer;
import com.github.matt159.dws.util.Constants;
import mods.railcraft.common.gui.containers.ContainerCokeOven;
import mods.railcraft.common.gui.containers.RailcraftContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerCokeOven.class)
public abstract class ContainerCokeOvenMixin extends RailcraftContainer implements IDWSContainer {
    @ModifyConstant(method = "<init>",
                    constant = {
                        @Constant(intValue = 90),
                        @Constant(intValue = 16),
                        @Constant(intValue = 62),
                        @Constant(intValue = 149)
                    },
                    require = 5)
    private int modifyXOffsets(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
