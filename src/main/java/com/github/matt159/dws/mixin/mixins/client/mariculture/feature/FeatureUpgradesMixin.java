package com.github.matt159.dws.mixin.mixins.client.mariculture.feature;

import mariculture.core.gui.feature.Feature;
import mariculture.core.gui.feature.FeatureUpgrades;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FeatureUpgrades.class)
public abstract class FeatureUpgradesMixin extends Feature {
    @ModifyConstant(method = "draw",
                    constant = @Constant(intValue = 176, ordinal = 0),
                    require = 1,
                    remap = false)
    private int modifyDrawXOffset(int constant) {
        return constant + 161;
    }
}
