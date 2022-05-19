package com.github.matt159.dws.mixins.client.minecraft;

import com.github.matt159.dws.Tags;
import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.interfaces.minecraft.IGuiMixin;
import com.github.matt159.dws.util.DWSUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiCrafting.class)
public abstract class GuiCraftingMixin extends GuiContainer implements IDWSGui {

    private static final ResourceLocation location = new ResourceLocation(Tags.MODID, "textures/minecraft/gui/container/crafting_table.png");
    private static final int X_SIZE = 338;
    private static final int Y_SIZE = 166;

    public GuiCraftingMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At(   value = "RETURN"),
            require = 1)
    private void updateGuiSize(InventoryPlayer inventoryPlayer, World world, int x, int y, int z, CallbackInfo ci) {
        ((GuiCrafting) (Object) (this)).xSize = X_SIZE;
        ((GuiCrafting) (Object) (this)).ySize = Y_SIZE;
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
                            target = "Lnet/minecraft/client/gui/inventory/GuiCrafting;drawTexturedModalRect(IIIIII)V"),
                require = 1)
    private void rerouteDrawCall(GuiCrafting instance, int x, int y, int u, int v, int w, int h) {
        DWSUtil.drawTexturedModalRect(x, y, u, v, w, h, zLevel);
    }
}
