package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.widgets.IScrollSource;
import appeng.client.gui.widgets.ISortSource;
import appeng.client.me.ItemRepo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRepo.class)
public abstract class ItemRepoMixin {
    @Shadow(remap = false)
    private int rowSize;

    @Inject(method = "<init>",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private void injectLargerRowSize(IScrollSource src, ISortSource sortSrc, CallbackInfo ci) {
        this.rowSize = 13;
    }
}
