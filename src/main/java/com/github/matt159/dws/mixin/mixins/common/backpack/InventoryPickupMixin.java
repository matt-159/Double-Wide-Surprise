package com.github.matt159.dws.mixin.mixins.common.backpack;

import de.eydamos.backpack.inventory.InventoryPickup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(InventoryPickup.class)
public abstract class InventoryPickupMixin {
    @ModifyConstant(method = {"<init>",
                              "setInventoryContent"},
                    constant = @Constant(intValue = 9),
                    remap = false,
                    require = 2)
    private int modifyPickupSlotCount(int constant) {
        return 18;
    }
}
