package com.github.matt159.dws.mixin.mixins.client.minecraft.inventory;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;

@Mixin(InventoryPlayer.class)
public abstract class InventoryPlayerMixin implements IInventory {
    @Shadow
    public int currentItem;

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
