package com.github.matt159.dws.mixin.mixins.common.backpack;

import de.eydamos.backpack.saves.BackpackSave;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BackpackSave.class)
public abstract class BackpackSaveMixin {
    @Inject(method = "getSlotsPerRow",
            at = @At("HEAD"),
            cancellable = true,
            remap = false,
            require = 1)
    private void injectNewSlotsPerRow(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(18);
        cir.cancel();
    }
}
