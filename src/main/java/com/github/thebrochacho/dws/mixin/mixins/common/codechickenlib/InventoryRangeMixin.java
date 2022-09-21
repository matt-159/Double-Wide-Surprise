package com.github.thebrochacho.dws.mixin.mixins.common.codechickenlib;

import codechicken.lib.inventory.InventoryRange;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(InventoryRange.class)
public abstract class InventoryRangeMixin {

    @Shadow(remap = false)
    public int[] slots;

//    @Inject(method = "<init>(Lnet/minecraft/inventory/IInventory;I)V",
//            at = @At("RETURN"),
//            remap = false,
//            require = 1)
//    private void injectDWSInventoryRange(IInventory inv, int side, CallbackInfo ci) {
//        if (inv instanceof InventoryDWS) {
//            reorderSlots();
//        }
//    }
//
//    @Inject(method = "<init>(Lnet/minecraft/inventory/IInventory;II)V",
//            at = @At("RETURN"),
//            remap = false,
//            require = 1)
//    private void injectDWSInventoryRange(IInventory inv, int fslot, int size, CallbackInfo ci) {
//        if (inv instanceof InventoryDWS) {
//            reorderSlots();
//        }
//    }

    private void reorderSlots() {
        int index = 0;

        // Left half of hotbar
        for (int i = 0; i < 9; i++) {
            slots[index++] = i;
        }

        // Right half of hotbar
        for (int i = 63; i < 72; i++) {
            slots[index++] = i;
        }

        // Main player inventory
        for (int i = 9; i < 63; i++) {
            slots[index++] = i;
        }
    }
}
