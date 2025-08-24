package com.github.matt159.dws.mixin.mixins.common.dws.baubles;

import baubles.common.container.InventoryBaubles;
import com.github.matt159.dws.inventory.slots.MixAndMatchBaublesSlot;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(InventoryBaubles.class)
public abstract class InventoryBaublesMixin {
    @WrapOperation(method = "isItemValidForSlot",
                   at = @At(value = "INVOKE",
                            target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z"),
                   require = 1)
    private boolean checkWildcard(String instance, Object o, Operation<Boolean> original) {
        return MixAndMatchBaublesSlot.TYPE.equals(instance) || original.call(instance, o);
    }
}
