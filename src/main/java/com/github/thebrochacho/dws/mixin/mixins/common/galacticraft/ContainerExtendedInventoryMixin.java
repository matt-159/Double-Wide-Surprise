package com.github.thebrochacho.dws.mixin.mixins.common.galacticraft;

import micdoodle8.mods.galacticraft.core.inventory.ContainerExtendedInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerExtendedInventory.class)
public abstract class ContainerExtendedInventoryMixin {

    @ModifyConstant(method="<init>",
                    constant = @Constant(intValue = 39),
                    require = 1)
    private int modifyArmorSlotOffset(int constant) {
        return 75;
    }
}
