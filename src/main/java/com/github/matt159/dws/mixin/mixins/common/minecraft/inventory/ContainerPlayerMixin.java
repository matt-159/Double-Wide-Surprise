package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import net.minecraft.inventory.ContainerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerPlayer.class)
public abstract class ContainerPlayerMixin {

    @ModifyConstant(method = "<init>",
                    constant = {    @Constant(intValue = 88),
                                    @Constant(intValue = 144)   },
                    require = 1)
    private int modifyCraftingSlotsXOffset(int constant) {
        return constant + 162;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private int modifyColumnCount(int constant) {
        return 18;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 36),
                    require = 4)
    private int modifyHotbarSlotStart(int constant) {
        return 63;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 45),
                    require = 6)
    private int modifyHotbarSlotEnd(int constant) {
        return 81;
    }
}
