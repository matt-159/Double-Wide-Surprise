package com.github.matt159.dws.mixins.common.minecraft.inventory;

import net.minecraft.entity.player.InventoryPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryPlayer.class)
public abstract class InventoryPlayerMixin {
    @Shadow public int currentItem;

    @ModifyConstant(method = {  "<init>",
                                "readFromNBT"   },
                    constant = @Constant(intValue = 36),
                    require = 1)
    private int modifyInventoryArraySize(int constant) {
        return 72;
    }

    @ModifyConstant(method = "getHotbarSize",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private static int modifyHotbarSize(int constant) {
        return 18;
    }

    @Inject(method = "changeCurrentItem",
            at = @At("HEAD"),
            cancellable = true,
            require = 1)
    private void injectChangeCurrentItem(int delta, CallbackInfo ci) {
        delta = Math.min(1, delta);
        delta = Math.max(-1, delta);

        // -= because scrolling down should decrement the selected slot
        this.currentItem -= delta;

        if (this.currentItem < 0) {
            this.currentItem = 17;
        }

        if (this.currentItem > 17) {
            this.currentItem = 0;
        }
        //cancelling it here because the vanilla implementation is really dumb
        ci.cancel();
    }
}
