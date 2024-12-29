package com.github.matt159.dws.mixin.mixins.common.thaumcraft;

import com.github.matt159.dws.interfaces.IDWSContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.common.entities.golems.ContainerGolem;

import net.minecraft.inventory.Container;

@Mixin(ContainerGolem.class)
public abstract class ContainerGolemMixin extends Container implements IDWSContainer {
    @ModifyConstant(method = "bindPlayerInventory",
                    constant = @Constant(intValue = 9),
                    remap = false,
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
