package com.github.matt159.dws.mixin.mixins.client.chisel;

import com.github.matt159.dws.util.TextureWhitelist;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.chisel.client.gui.GuiChisel;
import team.chisel.client.gui.GuiScrollbar;

@Mixin(GuiScrollbar.class)
public abstract class GuiScrollbarMixin {
    @Shadow(remap = false)
    private int rowSize;

    @Shadow(remap = false)
    private int rowsOnPage;

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    private void updateRowInfo(CallbackInfo ci) {
        this.rowSize = 15;
        this.rowsOnPage = 4;
    }

    @Inject(method = "draw",
            at = @At("HEAD"),
            remap = false,
            require = 1)
    private void injectStateEntry(GuiChisel g, CallbackInfo ci) {
        TextureWhitelist.isBadContext = true;
    }

    @Inject(method = "draw",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private void injectStateExit(GuiChisel g, CallbackInfo ci) {
        TextureWhitelist.isBadContext = false;
    }
}
