package com.github.matt159.dws.mixin.mixins.common.baublesexpanded;

import baubles.api.expanded.BaubleExpandedSlots;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BaubleExpandedSlots.class)
public abstract class BaublesExpandedSlotsMixin {
//    @Inject(method = "isTypeRegistered",
//            at = @At("HEAD"),
//            cancellable = true,
//            require = 1,
//            remap = false)
//    private static void injectIsTypeRegistered(String type, CallbackInfoReturnable<Boolean> cir) {
//        if ("any".equalsIgnoreCase(type)) {
//            cir.setReturnValue(true);
//            cir.cancel();
//        }
//    }

    @SuppressWarnings("all")
    @ModifyConstant(method = {
                        "/(?!<init>)/"
                    },
                    constant = @Constant(intValue = 20),
                    remap = false)
    private static int modifyMaxSlots(int constant) {
        return 36;
    }
}
