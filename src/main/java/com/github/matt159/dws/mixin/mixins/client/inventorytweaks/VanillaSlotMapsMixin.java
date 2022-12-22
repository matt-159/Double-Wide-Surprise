package com.github.matt159.dws.mixin.mixins.client.inventorytweaks;

import com.github.matt159.dws.util.DWSUtil;
import invtweaks.containers.VanillaSlotMaps;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

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
