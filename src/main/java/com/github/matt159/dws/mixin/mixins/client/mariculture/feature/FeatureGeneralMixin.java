package com.github.matt159.dws.mixin.mixins.client.mariculture.feature;

import mariculture.core.gui.feature.Feature;
import mariculture.core.gui.feature.FeatureEject;
import mariculture.core.gui.feature.FeatureRedstone;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = {
        FeatureEject.class,
        FeatureRedstone.class,
})
public abstract class FeatureGeneralMixin extends Feature {
    @ModifyConstant(method = "*",
                    constant = {
                        @Constant(intValue = 175),
                        @Constant(intValue = 177),
                        @Constant(intValue = 192)
                    },
                    require = 1)
    private int modifyFeatureXOffsets(int constant) {
        return constant + 161;
    }
}
