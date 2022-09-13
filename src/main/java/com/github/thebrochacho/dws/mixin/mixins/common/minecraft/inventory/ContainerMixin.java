package com.github.thebrochacho.dws.mixin.mixins.common.minecraft.inventory;

import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Container.class)
public abstract class ContainerMixin {

    @ModifyConstant(method = "slotClick",
                    constant = @Constant(intValue = 9))
    private int modifyHotbarRangeCheck(int constant) {
        return 18;
    }
}
