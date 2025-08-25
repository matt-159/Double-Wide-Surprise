package com.github.matt159.dws.mixin.mixins.client.minecraft.gui;

import com.github.matt159.dws.config.DWSConfig;
import com.github.matt159.dws.events.keybinds.InventorySwapKey;
import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.inventory.slots.SlotDWS;
import com.github.matt159.dws.network.DWSInventorySwapPacket;
import com.github.matt159.dws.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.github.matt159.dws.registry.Keybindings;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static com.github.matt159.dws.util.DWSUtil.drawTexturedRect;

@Mixin(GuiContainer.class)
public abstract class GuiContainerMixin extends GuiScreen {

    @Shadow
    public Container inventorySlots;

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifyDefaultXSize(int constant) {
        return (this instanceof IDWSGui) ? 338 : 176;
    }

    // Doing this goofy-aah shit because normal hotkeys don't work inside guis
    @Inject(method = "keyTyped",
            at = @At(value = "TAIL"),
            require = 1)
    private void injectDWSHotkey(char typedChar, int key, CallbackInfo ci) {
        if (Keybindings.SwapKey.getKeyCode() == key && !(this instanceof IDWSGui)) {
            PacketHandler.INSTANCE.sendToServer(new DWSInventorySwapPacket(true));
        }
    }

    @Inject(method = "drawScreen",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/client/gui/inventory/GuiContainer;drawGuiContainerForegroundLayer(II)V",
                     shift = At.Shift.AFTER),
            require = 1)
    private void injectDebugStringDrawCalls(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        if (DWSConfig.General.showDebugSlotInfo) {
            for (int i = 0; i < this.inventorySlots.inventorySlots.size(); ++i) {
                Slot slot = (Slot) this.inventorySlots.inventorySlots.get(i);

                String slotNumber = Integer.toString(slot.slotNumber);
                String slotIndex = Integer.toString(slot.getSlotIndex());

                this.fontRendererObj.drawString(slotNumber, slot.xDisplayPosition, slot.yDisplayPosition, Color.CYAN.getRGB());
                this.fontRendererObj.drawString(slotIndex, slot.xDisplayPosition, slot.yDisplayPosition + 8, Color.MAGENTA.getRGB());
            }
        }
    }

    @Redirect(method = "func_146977_a",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/inventory/Slot;getBackgroundIconIndex()Lnet/minecraft/util/IIcon;"),
              require = 1)
    private IIcon drawBackgroundTexture(Slot instance) {
        IIcon icon = instance.getBackgroundIconIndex();
        ResourceLocation slotHint = instance.getBackgroundIconTexture();

        if (icon == null && slotHint != null && instance instanceof SlotDWS) {
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);

            drawTexturedRect(slotHint,
                             instance.xDisplayPosition,
                             instance.yDisplayPosition,
                             16,
                             16,
                             this.zLevel);
        }

        return icon;
    }

    @Inject(method = "func_146977_a",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V",
                     shift = At.Shift.BEFORE),
            require = 1)
    private void injectGLBlendFunction(Slot slot, CallbackInfo ci) {
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
    }
}
