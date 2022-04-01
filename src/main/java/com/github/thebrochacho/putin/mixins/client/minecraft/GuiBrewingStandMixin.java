package com.github.thebrochacho.putin.mixins.client.minecraft;

import com.github.thebrochacho.putin.Tags;
import com.github.thebrochacho.putin.interfaces.IPutinGui;
import com.github.thebrochacho.putin.interfaces.minecraft.IGuiMixin;
import com.github.thebrochacho.putin.util.PutinUtil;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiBrewingStand.class)
public class GuiBrewingStandMixin implements IPutinGui {
    @Shadow private TileEntityBrewingStand tileBrewingStand;
    private static final ResourceLocation location = new ResourceLocation(Tags.MODID, "textures/minecraft/brewing_stand.png");
    private static final int X_SIZE = 338;
    private static final int Y_SIZE = 166;

    @Inject(method = "<init>",
            at = @At(   value = "RETURN",
                        remap = false),
            require = 1)
    private void updateGuiSize(InventoryPlayer p_i1081_1_, TileEntityBrewingStand p_i1081_2_, CallbackInfo ci) {
        ((GuiBrewingStand) (Object) (this)).xSize = X_SIZE;
        ((GuiBrewingStand) (Object) (this)).ySize = Y_SIZE;
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V",
                    remap = false),
            require = 1)
    private void rerouteBindTexture(TextureManager instance, ResourceLocation p_bindTexture_1_) {
        instance.bindTexture(location);
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
        float zLevel = ((IGuiMixin) (Object) (this)).getZLevel();

        int x = (gbs.width - gbs.xSize) / 2;
        int y = (gbs.height - gbs.ySize) / 2;
        PutinUtil.drawTexturedModalRect(x, y, 0, 0, gbs.xSize, gbs.ySize, zLevel);

        int brewTime = this.tileBrewingStand.getBrewTime();
        if (brewTime > 0) {
            int scale = (int) (28.0F * (1.0F - (float) brewTime / 400.0F));
            if (scale > 0) {
                PutinUtil.drawTexturedModalRect(x + 178, y + 16, gbs.xSize, 0, 9, scale, zLevel);
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
                PutinUtil.drawTexturedModalRect(x + 147, y + 14 + (29 - scale), 347, 29 - scale, 12, scale, zLevel);
            }
        }
        ci.cancel();
    }
}
