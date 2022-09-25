package com.github.thebrochacho.dws.mixin.mixins.common.nei;

import codechicken.nei.NEIServerUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(NEIServerUtils.class)
public abstract class NEIServerUtilsMixin {
    @ModifyConstant(method = "cycleCreativeInv",
                    constant = {    @Constant(  intValue = 9,
                                                slice = "one"),
                                    @Constant(  intValue = 9,
                                                slice = "two"),
                                    @Constant(  intValue = 9,
                                                slice = "three")  },
                    slice = {   @Slice( from = @At("HEAD"),
                                        to = @At(   value = "CONSTANT",
                                                    args = "intValue=9",
                                                    ordinal = 0),
                                        id = "one"),
                                @Slice( from = @At( value = "CONSTANT",
                                                    args = "intValue=9",
                                                    ordinal = 2),
                                        to = @At( value = "CONSTANT",
                                                    args = "intValue=9",
                                                    ordinal = 6),
                                        id = "two"),
                                @Slice( from = @At( value = "CONSTANT",
                                                    args = "intValue=9",
                                                    ordinal = 8),
                                        to = @At("TAIL"),
                                        id = "three")   },
                    remap = false,
                    require = 1)
    private static int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
