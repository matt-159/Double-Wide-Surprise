package com.github.matt159.putin.mixins.common.minecraft;

import com.github.matt159.putin.Tags;
import com.github.matt159.putin.gui.PutinGui;
import com.github.matt159.putin.util.PutinUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiContainer.class)
@SideOnly(Side.CLIENT)
public abstract class GuiContainerMixin {
    private static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation(Tags.MODID, "textures/putinv_overlay.png");
    private static int OVERLAY_WIDTH = 338;
    private static int OVERLAY_HEIGHT = 88;

    @Shadow public Container inventorySlots;

    @Inject(method = "drawScreen",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/inventory/GuiContainer;drawGuiContainerBackgroundLayer(FII)V",
                    shift = At.Shift.AFTER),
            require = 1)
    @SideOnly(Side.CLIENT)
    private void afterDrawScreen(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        if (!((Object) this instanceof PutinGui)) {
            Slot slot = (Slot) inventorySlots.getSlot(inventorySlots.inventorySlots.size() - 36 - 1);

            Minecraft.getMinecraft().getTextureManager().bindTexture(OVERLAY_TEXTURE);

            int guiLeft = (((GuiScreen)(Object)this).width - OVERLAY_WIDTH) / 2;
//            int guiTop = slot.yDisplayPosition;
            int guiTop = (((GuiScreen)(Object)this).height - OVERLAY_HEIGHT) / 2;

            PutinUtil.drawTexturedModalRect(guiLeft, guiTop, 0, 0, OVERLAY_WIDTH, OVERLAY_HEIGHT, 1000);
        }
    }
}
