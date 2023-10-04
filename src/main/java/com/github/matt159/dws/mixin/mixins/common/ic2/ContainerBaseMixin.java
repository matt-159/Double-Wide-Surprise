package com.github.matt159.dws.mixin.mixins.common.ic2;

import com.github.matt159.dws.interfaces.IDWSContainer;
import ic2.core.ContainerBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.inventory.Container;

@Mixin(ContainerBase.class)
public abstract class ContainerBaseMixin extends Container {
    @ModifyConstant(method = "addPlayerInventorySlots(Lnet/minecraft/entity/player/EntityPlayer;II)V",
                    constant = @Constant(intValue = 9),
                    remap = false,
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return this instanceof IDWSContainer ? 18 : constant;
    }
}
