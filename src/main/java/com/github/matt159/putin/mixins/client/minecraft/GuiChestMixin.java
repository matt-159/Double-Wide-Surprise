package com.github.matt159.putin.mixins.client.minecraft;

import com.github.matt159.putin.Tags;
import com.github.matt159.putin.interfaces.IPutinGui;
import com.github.matt159.putin.interfaces.minecraft.IGuiMixin;
import com.github.matt159.putin.util.PutinUtil;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiChest.class)
public class GuiChestMixin implements IPutinGui {

    private static final ResourceLocation location = new ResourceLocation(Tags.MODID, "textures/minecraft/generic_54.png");
    private static final int X_SIZE = 338;

    @Inject(method = "<init>",
            at = @At(   value = "RETURN",
                        remap = false),
            require = 1)
    private void updateGuiSize(IInventory p_i1083_1_, IInventory p_i1083_2_, CallbackInfo ci) {
        ((GuiChest) (Object) (this)).xSize = X_SIZE;
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V",
                            remap = false),
                require = 1)
    private void rerouteBindTexture(TextureManager instance, ResourceLocation p_bindTexture_1_) {
        instance.bindTexture(location);
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/gui/inventory/GuiChest;drawTexturedModalRect(IIIIII)V"),
                require = 1)
    private void rerouteDrawCall(GuiChest instance, int x, int y, int u, int v, int w, int h) {
        float zLevel = ((IGuiMixin) (Object) (this)).getZLevel();
        PutinUtil.drawTexturedModalRect(x, y, u, v, w, h, zLevel);
    }
}
