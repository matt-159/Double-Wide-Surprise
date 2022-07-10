package com.github.matt159.dws.mixins.client.minecraft;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.util.DWSUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntityFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiFurnace.class)
public abstract class GuiFurnaceMixin extends GuiContainer implements IDWSGui {

    @Shadow private TileEntityFurnace tileFurnace;

    public GuiFurnaceMixin(Container container) {
        super(container);
    }

    @Inject(method = "drawGuiContainerBackgroundLayer",
            at = @At(   value = "INVOKE",
                        target = "Lnet/minecraft/client/gui/inventory/GuiFurnace;drawTexturedModalRect(IIIIII)V",
                        ordinal = 0,
                        shift = At.Shift.BEFORE),
            cancellable = true,
            require = 1)
    private void rerouteDrawCall(float f1, int i1, int i2, CallbackInfo ci) {
        GuiFurnace gf = (GuiFurnace) (Object) (this);

        int x = (gf.width - gf.xSize) / 2;
        int y = (gf.height - gf.ySize) / 2;
        DWSUtil.drawTexturedModalRect(x, y, 0, 0, gf.xSize, gf.ySize, zLevel);

        if (this.tileFurnace.isBurning()) {
            int burnTime = this.tileFurnace.getBurnTimeRemainingScaled(13);
            DWSUtil.drawTexturedModalRect(x + 137, y + 36 + (12 - burnTime), 338, (12 - burnTime), 14, burnTime + 1, zLevel);
            burnTime = this.tileFurnace.getCookProgressScaled(24);
            DWSUtil.drawTexturedModalRect(x + 161, y + 34, 338, 14, burnTime + 1, 16, zLevel);
        }
        ci.cancel();
    }
}
