package com.github.matt159.dws.mixin.mixins.common.gardencore;

import com.github.matt159.dws.interfaces.IDWSContainer;
import com.github.matt159.dws.util.Constants;
import com.jaquadro.minecraft.gardencore.inventory.ContainerCompostBin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerCompostBin.class)
public abstract class ContainerCompostBinMixin implements IDWSContainer {
    @ModifyConstant(method = "<init>",
                    constant = {
                        @Constant(intValue = 9, ordinal = 1),
                        @Constant(intValue = 9, ordinal = 2),
                        @Constant(intValue = 9, ordinal = 3),
                        @Constant(intValue = 9, ordinal = 4)
                    },
                    require = 2)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }

    @ModifyConstant(method = "<init>",
                    constant = {
                        @Constant(intValue = 30),
                        @Constant(intValue = 123)
                    },
                    require = 2)
    private int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
