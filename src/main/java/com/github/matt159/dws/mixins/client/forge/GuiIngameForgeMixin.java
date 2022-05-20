package com.github.matt159.dws.mixins.client.forge;

import com.github.matt159.dws.Tags;
import com.github.matt159.dws.inventory.InventoryDWS;
import com.github.matt159.dws.util.DWSUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(GuiIngameForge.class)
public abstract class GuiIngameForgeMixin extends GuiIngame {

    private static final ResourceLocation location = new ResourceLocation(Tags.MODID, "textures/minecraft/gui/widgets.png");

    public GuiIngameForgeMixin(Minecraft minecraft) {
        super(minecraft);
    }

    private int width = 0;
    private int height = 0;
    private float partialTicks = 0;

    @Inject(method = "renderHotbar",
            at = @At("HEAD"),
            remap = false,
            require = 1)
    private void captureArgs(int width, int height, float partialTicks, CallbackInfo ci) {
        this.width = width;
        this.height = height;
        this.partialTicks = partialTicks;
    }

    @Redirect(  method = "renderHotbar",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V"),
                remap = false,
                require = 1)
    private void redirectBindTexture(TextureManager instance, ResourceLocation resourceLocation) {
        instance.bindTexture(location);
    }

    @Redirect(  method = "renderHotbar",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraftforge/client/GuiIngameForge;drawTexturedModalRect(IIIIII)V",
                            ordinal = 0),
                remap = false,
                require = 1)
    private void redirectFirstDrawCall(GuiIngameForge instance, int x, int y, int u, int v, int w, int h) {
        DWSUtil.drawTexturedModalRect(x, y, u, v, w, h, zLevel);
    }

    @Redirect(  method = "renderHotbar",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraftforge/client/GuiIngameForge;drawTexturedModalRect(IIIIII)V",
                            ordinal = 1 ),
                remap = false,
                require = 1)
    private void redirectSecondDrawCall(GuiIngameForge instance, int x, int y, int u, int v, int w, int h) {
        //Easier to do it like this than with wonky math
        int index = Arrays.binarySearch(InventoryDWS.HOTBAR_SLOTS, this.mc.thePlayer.inventory.currentItem);

        x = (this.width / 2) - 181 - 1 + index * 20;

        DWSUtil.drawTexturedModalRect(x, y, u, v, w, h, zLevel);
    }

    @ModifyConstant(method = "renderHotbar",
                    constant = {    @Constant(intValue = 90),
                                    @Constant(intValue = 91)    },
                    remap = false,
                    require = 1)
    private int modifyHotbarXStart(int constant) {
        return constant + 90;
    }

    @ModifyConstant(method = "renderHotbar",
                    constant = @Constant(intValue = 182),
                    remap = false,
                    require = 1)
    private int modifyTextureXSize(int constant) {
        return 362;
    }

    @Inject(method = "renderHotbar",
            at = @At(   value = "INVOKE",
                        target = "Lnet/minecraft/client/renderer/RenderHelper;disableStandardItemLighting()V",
                        shift = At.Shift.BEFORE),
            remap = false,
            require = 1)
    private void injectAddHotbarSlots(int width, int height, float partialTicks, CallbackInfo ci) {
        for (int i = 0; i < 9; i++) {
            int x = width / 2 - 180 + (i + 9) * 20 + 2;
            int z = height - 16 - 3;
            this.renderInventorySlot(63 + i, x, z, partialTicks);
        }
    }
}
