package com.github.thebrochacho.dws.mixins.common.minecraft.inventory;

import net.minecraft.entity.player.InventoryPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(InventoryPlayer.class)
public class InventoryPlayerMixin {

    @ModifyConstant(method="getHotbarSize",
                    constant = @Constant(intValue = 9))
    private static int modifyHotbarSize(int constant) {
        return 18;
    }
}
