package com.github.matt159.dws.mixin.mixins.client.forestry.core.gui;

import forestry.core.gui.GuiAlyzer;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(GuiAlyzer.class)
public abstract class GuiAlyzerMixin {
    @ModifyConstant(method = "drawAnalyticsOverview",
                    constant = { @Constant(intValue = 8),
                                 @Constant(intValue = 12),
                                 @Constant(intValue = 16) },
                    remap = false,
                    require = 1)
    private int modifyXOffsets_drawAnalyticsOverview(int constant) {
        return constant + 46;
    }

    @ModifyConstant(method = "drawAnalyticsPageClassification",
                    constant = { @Constant(intValue = 12,
                                           slice = "12_a"),
                                 @Constant(intValue = 12,
                                           slice = "12_b"),
                                 @Constant(intValue = 170),
                                 @Constant(intValue = 210) },
                    slice = { @Slice(from = @At("HEAD"),
                                     to = @At(value = "INVOKE",
                                              target = "Ljava/util/Stack;isEmpty()Z"),
                                     id = "12_a"),
                              @Slice(from = @At(value = "INVOKE",
                                                target = "Lforestry/api/genetics/IAlleleSpecies;getBinomial()Ljava/lang/String;"),
                                     to = @At("TAIL"),
                                     id = "12_b") },
                    remap = false,
                    require = 1)
    private int modifyXOffsets_drawAnalyticsPageClassification(int constant) {
        return constant + 46;
    }

    @ModifyConstant(method = "drawAnalyticsPageMutations",
                    constant = @Constant(intValue = 12),
                    remap = false,
                    require = 1)
    private int modifyXOffsets_drawAnalyticsPageMutations(int constant) {
        return constant + 46;
    }
}