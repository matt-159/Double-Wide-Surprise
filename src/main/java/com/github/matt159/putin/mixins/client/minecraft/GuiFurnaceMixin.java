package com.github.matt159.putin.mixins.client.minecraft;

import com.github.matt159.putin.Tags;
import com.github.matt159.putin.interfaces.IMinecraftGuiMixin;
import com.github.matt159.putin.util.PutinUtil;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiFurnace.class)
public class GuiFurnaceMixin {

    @Shadow private TileEntityFurnace tileFurnace;
    private static final ResourceLocation location = new ResourceLocation(Tags.MODID, "textures/minecraft/furnace.png");
    private static final int X_SIZE = 338;
    private static final int Y_SIZE = 166;

    @Inject(method = "<init>",
            at = @At(   value = "RETURN",
                        remap = false),
            require = 1)
    private void updateGuiSize(InventoryPlayer p_i1091_1_, TileEntityFurnace p_i1091_2_, CallbackInfo ci) {
        ((GuiFurnace) (Object) (this)).xSize = X_SIZE;
        ((GuiFurnace) (Object) (this)).ySize = Y_SIZE;
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
                        target = "Lnet/minecraft/client/gui/inventory/GuiFurnace;drawTexturedModalRect(IIIIII)V",
                        ordinal = 0,
                        shift = At.Shift.BEFORE,
                        remap = false),
            cancellable = true,
            require = 1)
    private void rerouteDrawCall(float p_drawGuiContainerBackgroundLayer_1_, int p_drawGuiContainerBackgroundLayer_2_, int p_drawGuiContainerBackgroundLayer_3_, CallbackInfo ci) {
        GuiFurnace gf = (GuiFurnace) (Object) (this);
        float zLevel = ((IMinecraftGuiMixin) (Object) (this)).getZLevel();

        int x = (gf.width - gf.xSize) / 2;
        int y = (gf.height - gf.ySize) / 2;
        PutinUtil.drawTexturedModalRect(x, y, 0, 0, gf.xSize, gf.ySize, zLevel);

        if (this.tileFurnace.isBurning()) {
            int burnTime = this.tileFurnace.getBurnTimeRemainingScaled(13);
            PutinUtil.drawTexturedModalRect(x + 137, y + 36 + (12 - burnTime), 338, (12 - burnTime), 14, burnTime + 1, zLevel);
            burnTime = this.tileFurnace.getCookProgressScaled(24);
            PutinUtil.drawTexturedModalRect(x + 161, y + 34, 338, 14, burnTime + 1, 16, zLevel);
        }
        ci.cancel();
    }
}
