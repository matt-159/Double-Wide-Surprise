package com.github.thebrochacho.putin.mixins;

import com.github.thebrochacho.putin.Tags;
import com.github.thebrochacho.putin.gui.PutinGui;
import com.github.thebrochacho.putin.util.PutinUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreen.class)
public abstract class GuiScreenMixin {
    private static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation(Tags.MODID, "textures/putinv_overlay.png");
    private static int OVERLAY_WIDTH = 338;
    private static int OVERLAY_HEIGHT = 88;

    @Shadow public Minecraft mc;

    @Inject(method = "drawScreen",
            at = @At(value = "RETURN"),
            require = 1)
    @SideOnly(Side.CLIENT)
    private void afterDrawScreen(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        if (((Object) this instanceof GuiContainer) && !((Object) this instanceof PutinGui)) {
            GuiContainer gc = (GuiContainer) (Object) this;
            Slot slot = (Slot) gc.inventorySlots.inventorySlots.get(gc.inventorySlots.inventorySlots.size() - 36 - 1);

            this.mc.getTextureManager().bindTexture(OVERLAY_TEXTURE);

            int guiLeft = (((GuiScreen)(Object)this).width - OVERLAY_WIDTH) / 2;
            int guiTop = slot.yDisplayPosition;
//            int guiTop = (((GuiScreen)(Object)this).height - OVERLAY_HEIGHT) / 2;

            PutinUtil.drawTexturedModalRect(guiLeft, guiTop, 0, 0, OVERLAY_WIDTH, OVERLAY_HEIGHT, 1000);
        }
    }
}
