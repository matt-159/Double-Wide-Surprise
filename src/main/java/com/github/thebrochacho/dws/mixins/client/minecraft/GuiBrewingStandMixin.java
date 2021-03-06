package com.github.thebrochacho.dws.mixins.client.minecraft;

import com.github.thebrochacho.dws.interfaces.IDWSGui;
import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntityBrewingStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiBrewingStand.class)
public abstract class GuiBrewingStandMixin extends GuiContainer implements IDWSGui {
    @Shadow private TileEntityBrewingStand tileBrewingStand;

    public GuiBrewingStandMixin(Container container) {
        super(container);
    }

    @Inject(method = "drawGuiContainerBackgroundLayer",
            at = @At(   value = "INVOKE",
                        target = "Lnet/minecraft/client/gui/inventory/GuiBrewingStand;drawTexturedModalRect(IIIIII)V",
                        ordinal = 0,
                        shift = At.Shift.BEFORE),
            cancellable = true,
            require = 1)
    private void rerouteDrawCall(float f, int i1, int i2, CallbackInfo ci) {
        GuiBrewingStand gbs = (GuiBrewingStand) (Object) (this);

        int x = (gbs.width - gbs.xSize) / 2;
        int y = (gbs.height - gbs.ySize) / 2;
        DWSUtil.drawTexturedModalRect(x, y, 0, 0, gbs.xSize, gbs.ySize, zLevel);

        int brewTime = this.tileBrewingStand.getBrewTime();
        if (brewTime > 0) {
            int scale = (int) (28.0F * (1.0F - (float) brewTime / 400.0F));
            if (scale > 0) {
                DWSUtil.drawTexturedModalRect(x + 178, y + 16, gbs.xSize, 0, 9, scale, zLevel);
            }

            switch(brewTime / 2 % 7) {
                case 0:
                    scale = 29;
                    break;
                case 1:
                    scale = 24;
                    break;
                case 2:
                    scale = 20;
                    break;
                case 3:
                    scale = 16;
                    break;
                case 4:
                    scale = 11;
                    break;
                case 5:
                    scale = 6;
                    break;
                case 6:
                    scale = 0;
                    break;
            }

            if (scale > 0) {
                DWSUtil.drawTexturedModalRect(x + 147, y + 14 + (29 - scale), 347, 29 - scale, 12, scale, zLevel);
            }
        }
        ci.cancel();
    }
}
