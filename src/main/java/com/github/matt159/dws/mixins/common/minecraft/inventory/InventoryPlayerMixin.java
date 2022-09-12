package com.github.matt159.dws.mixins.common.minecraft.inventory;

import net.minecraft.entity.player.InventoryPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(InventoryPlayer.class)
public abstract class InventoryPlayerMixin {

    @ModifyConstant(method = {  "<init>",
                                "readFromNBT"   },
                    constant = @Constant(intValue = 36),
                    require = 1)
    private int modifyInventoryArraySize(int constant) {
        return 72;
    }

    @ModifyConstant(method = "getHotbarSize",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private static int modifyHotbarSize(int constant) {
        return 18;
    }
}
