package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import net.minecraft.entity.player.InventoryPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(InventoryPlayer.class)
public abstract class InventoryPlayerMixin {

    @ModifyConstant(method="getHotbarSize",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private static int modifyHotbarSize(int constant) {
        return 18;
    }
}
