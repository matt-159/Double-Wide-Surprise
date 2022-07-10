package com.github.matt159.dws.mixins.common.gregtech;

import com.github.matt159.dws.util.DWSUtil;
import gregtech.api.gui.GT_Container;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GT_Container.class)
public abstract class GT_ContainerMixin extends Container {
    @Inject(method = "bindPlayerInventory",
            at = @At("HEAD"),
            cancellable = true,
            remap = false,
            require = 1)
    private void injectAddDWSSLots(InventoryPlayer aInventoryPlayer, CallbackInfo ci) {
        DWSUtil.addDWSSlotsToContainer(this, aInventoryPlayer);
        ci.cancel();
    }
}
