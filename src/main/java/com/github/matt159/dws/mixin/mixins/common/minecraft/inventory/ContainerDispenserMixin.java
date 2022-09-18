package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerDispenser;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerDispenser.class)
public abstract class ContainerDispenserMixin extends Container {

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
