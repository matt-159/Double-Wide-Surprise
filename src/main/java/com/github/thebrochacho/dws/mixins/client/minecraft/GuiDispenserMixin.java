package com.github.thebrochacho.dws.mixins.client.minecraft;

import com.github.thebrochacho.dws.Tags;
import com.github.thebrochacho.dws.interfaces.IDWSGui;
import com.github.thebrochacho.dws.interfaces.minecraft.IGuiMixin;
import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiDispenser.class)
public abstract class GuiDispenserMixin extends GuiContainer implements IDWSGui {

    private static final ResourceLocation location = new ResourceLocation(Tags.MODID, "textures/minecraft/gui/container/dispenser.png");
    private static final int X_SIZE = 338;
    private static final int Y_SIZE = 166;

    public GuiDispenserMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At(   value = "RETURN"),
            require = 1)
    private void updateGuiSize(InventoryPlayer inventoryPlayer, TileEntityDispenser tileEntityDispenser, CallbackInfo ci) {
        ((GuiDispenser) (Object) (this)).xSize = X_SIZE;
        ((GuiDispenser) (Object) (this)).ySize = Y_SIZE;
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                        target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V"),
                require = 1)
    private void rerouteBindTexture(TextureManager instance, ResourceLocation resourceLocation) {
        instance.bindTexture(location);
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/gui/inventory/GuiDispenser;drawTexturedModalRect(IIIIII)V"),
                require = 1)
    private void rerouteDrawCall(GuiDispenser instance, int x, int y, int u, int v, int w, int h) {
        DWSUtil.drawTexturedModalRect(x, y, u, v, w, h, zLevel);
    }
}
