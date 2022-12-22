package com.github.matt159.dws.mixin.mixins.client.inventorytweaks;

import invtweaks.containers.VanillaSlotMaps;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(VanillaSlotMaps.class)
public abstract class VanillaSlotMapsMixin {
    @ModifyConstant(method = "*",
                    constant = @Constant(intValue = 36),
                    remap = false,
                    require = 0)
    private static int modifyInventorySize(int constant) {
        return constant * 2;
    }
}
