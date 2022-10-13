package com.github.matt159.dws.mixin.mixins.client.nei;

import codechicken.nei.GuiExtendedCreativeInv;
import com.github.matt159.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiExtendedCreativeInv.class)
public abstract class GuiExtendedCreativeInvMixin extends GuiContainer implements IDWSGui {

    public GuiExtendedCreativeInvMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private void injectNewXSize(Container container, CallbackInfo ci) {
        this.xSize = 361;
        this.ySize = 204;
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
            at = @At(   value = "INVOKE",
                    target = "Lcodechicken/nei/GuiExtendedCreativeInv;drawTexturedModalRect(IIIIII)V"),
            require = 1)
    private void redirectDrawBackground(GuiExtendedCreativeInv instance, int x, int y, int u, int v, int w, int h) {
        x = (this.width - this.xSize) / 2;
        this.drawTexturedModalRect(x, y, u, v, this.xSize, this.ySize);
    }

    @ModifyConstant(method = {  "getItemSpawnSlots",
                                "getInventoryAreas" },
            constant = @Constant(intValue = 54),
            remap = false,
            require = 2)
    private int modifyInventorySize(int constant) {
        return 108;
    }
}
