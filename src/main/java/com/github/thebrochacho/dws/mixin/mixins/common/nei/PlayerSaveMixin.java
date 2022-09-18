package com.github.thebrochacho.dws.mixin.mixins.common.nei;

import codechicken.nei.PlayerSave;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerSave.class)
public abstract class PlayerSaveMixin {
    @ModifyConstant(method = "loadCreativeInv",
                    constant = @Constant(intValue = 54),
                    remap = false,
                    require = 1)
    private int modifyInventorySize(int constant) {
        return 108;
    }
}
