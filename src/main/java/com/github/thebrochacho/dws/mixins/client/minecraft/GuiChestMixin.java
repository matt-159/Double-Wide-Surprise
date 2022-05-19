package com.github.thebrochacho.dws.mixins.client.minecraft;

import com.github.thebrochacho.dws.Tags;
import com.github.thebrochacho.dws.interfaces.IDWSGui;
import com.github.thebrochacho.dws.interfaces.minecraft.IGuiMixin;
import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiChest.class)
public abstract class GuiChestMixin extends GuiContainer implements IDWSGui {

    private static final ResourceLocation location = new ResourceLocation(Tags.MODID, "textures/minecraft/generic_54.png");
    private static final int X_SIZE = 338;

    public GuiChestMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void updateGuiSize(IInventory upperChest, IInventory lowerChest, CallbackInfo ci) {
        ((GuiChest) (Object) (this)).xSize = X_SIZE;
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V"),
                require = 1)
    private void rerouteBindTexture(TextureManager instance, ResourceLocation p_bindTexture_1_) {
        instance.bindTexture(location);
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/gui/inventory/GuiChest;drawTexturedModalRect(IIIIII)V"),
                require = 1)
    private void rerouteDrawCall(GuiChest instance, int x, int y, int u, int v, int w, int h) {
        DWSUtil.drawTexturedModalRect(x, y, u, v, w, h, zLevel);
    }
}