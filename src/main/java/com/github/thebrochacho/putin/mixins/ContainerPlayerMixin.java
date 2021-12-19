package com.github.thebrochacho.putin.mixins;

import net.minecraft.inventory.ContainerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerPlayer.class)
public abstract class ContainerPlayerMixin {
    private static final int CRAFTING_SLOT_X_OFFSET = 162;

    @ModifyConstant(method="<init>",
                    constant = @Constant(intValue = 88,
                                        ordinal = 0),
                    require = 1)
    private int shiftCraftingMatrix(int value) {
        return value + CRAFTING_SLOT_X_OFFSET;
    }

    @ModifyConstant(method="<init>",
                    constant = @Constant(intValue = 144,
                                        ordinal = 0),
                    require = 1)
    private int shiftResultMatrix(int value) {
        return value + CRAFTING_SLOT_X_OFFSET;
    }
}
