package com.github.thebrochacho.putin.mixins.client.minecraft;

import com.github.thebrochacho.putin.Tags;
import com.github.thebrochacho.putin.interfaces.IPutinGui;
import com.github.thebrochacho.putin.interfaces.minecraft.IGuiMixin;
import com.github.thebrochacho.putin.util.PutinUtil;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiDispenser.class)
public class GuiDispenserMixin implements IPutinGui {

    private static final ResourceLocation location = new ResourceLocation(Tags.MODID, "textures/minecraft/dispenser.png");
    private static final int X_SIZE = 338;
    private static final int Y_SIZE = 166;

    @Inject(method = "<init>",
            at = @At(   value = "RETURN",
                        remap = false),
            require = 1)
    private void updateGuiSize(InventoryPlayer p_i1098_1_, TileEntityDispenser p_i1098_2_, CallbackInfo ci) {
        ((GuiDispenser) (Object) (this)).xSize = X_SIZE;
        ((GuiDispenser) (Object) (this)).ySize = Y_SIZE;
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
                            target = "Lnet/minecraft/client/gui/inventory/GuiDispenser;drawTexturedModalRect(IIIIII)V"),
                require = 1)
    private void rerouteDrawCall(GuiDispenser instance, int x, int y, int u, int v, int w, int h) {
        float zLevel = ((IGuiMixin) (Object) (this)).getZLevel();
        PutinUtil.drawTexturedModalRect(x, y, u, v, w, h, zLevel);
    }
}
