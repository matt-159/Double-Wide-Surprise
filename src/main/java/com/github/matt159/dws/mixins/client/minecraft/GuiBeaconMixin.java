package com.github.matt159.dws.mixins.client.minecraft;

import com.github.matt159.dws.Tags;
import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.interfaces.minecraft.IGuiMixin;
import com.github.matt159.dws.util.DWSUtil;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiBeacon.class)
public abstract class GuiBeaconMixin extends GuiContainer implements IDWSGui {

    private static final ResourceLocation location = new ResourceLocation(Tags.MODID, "textures/minecraft/beacon.png");
    private static final int X_SIZE = 338;
    private static final int Y_SIZE = 219;

    public GuiBeaconMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void updateGuiSize(InventoryPlayer inventoryPlayer, TileEntityBeacon tileEntityBeacon, CallbackInfo ci) {
        ((GuiBeacon) (Object) (this)).xSize = X_SIZE;
        ((GuiBeacon) (Object) (this)).ySize = Y_SIZE;
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
                            target = "Lnet/minecraft/client/gui/inventory/GuiBeacon;drawTexturedModalRect(IIIIII)V"),
                require = 1)
    private void rerouteDrawCall(GuiBeacon instance, int x, int y, int u, int v, int w, int h) {
        DWSUtil.drawTexturedModalRect(x, y, u, v, w, h, zLevel);
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 164),
                    require = 1)
    private int modifyConfirmButtonXOffset(int constant) {
        return 219;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 190),
                    require = 1)
    private int modifyCancelButtonXOffset(int constant) {
        return 244;
    }

    @ModifyConstant(method = "updateScreen",
                    constant = @Constant(intValue = 76),
                    require = 1)
    private int modifyPrimaryButtonXOffset(int constant) {
        return 130;
    }

    @ModifyConstant(method = "updateScreen",
                    constant = @Constant(intValue = 167),
                    require = 1)
    private int modifySecondaryButtonXOffset(int constant) {
        return 221;
    }

    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
            constant = @Constant(intValue = 62),
            require = 1)
    private int modifyBeaconPrimaryStringXOffset(int constant) {
        return 116;
    }

    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 169),
                    require = 1)
    private int modifyBeaconSecondaryStringXOffset(int constant) {
        return 223;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 42),
                    require = 1)
    private int modifyItemRenderXOffset(int constant) {
        return 96;
    }
}
