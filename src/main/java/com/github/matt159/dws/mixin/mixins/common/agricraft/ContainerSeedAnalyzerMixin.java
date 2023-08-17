package com.github.matt159.dws.mixin.mixins.common.agricraft;

import com.InfinityRaider.AgriCraft.container.ContainerSeedAnalyzer;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerSeedAnalyzer.class)
public class ContainerSeedAnalyzerMixin {
    @ModifyConstant(method = "*",
                    constant = {
                        @Constant(intValue = 36),
                        @Constant(intValue = 37),
                        @Constant(intValue = 38)
                    },
                    remap = false,
                    require = 1)
    private int modifySlotIndices(int constant) {
        return constant + 36;
    }

    @ModifyConstant(method = "addSlots",
                    constant = {
                        @Constant(intValue = 80),
                        @Constant(intValue = 152)
                    },
                    remap = false,
                    require = 2)
    private int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
