package com.github.matt159.dws.mixin.mixins.common.inventorytweaks;

import invtweaks.InvTweaksConfigInventoryRuleset;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(InvTweaksConfigInventoryRuleset.class)
public abstract class InvTweaksConfigInventoryRulesetMixin {
    @ModifyConstant(method = "*",
                    constant = { @Constant(intValue = 9),
                                 @Constant(intValue = 36) },
                    remap = false,
                    require = 9)
    private int modifyInventorySize(int constant) {
        return constant * 2;
    }
}
