package com.github.thebrochacho.dws.mixins.client.minecraft;

import com.github.thebrochacho.dws.interfaces.IDWSGui;
import com.github.thebrochacho.dws.network.DWSInventorySwapPacket;
import com.github.thebrochacho.dws.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Mixin(GuiContainer.class)
public abstract class GuiContainerMixin extends GuiScreen {

    @Shadow public int xSize;

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void injectNewDefaultXSize(Container container, CallbackInfo ci) {
        this.xSize = 338;
    }

    @Inject(method = "keyTyped",
            at = @At(value = "TAIL"),
            require = 1)
    private void injectDWSHotkey(char p_73869_1_, int key, CallbackInfo ci) {
        if (this instanceof IDWSGui)
            return;

        KeyBinding swapKey;
        try {
            String description = StatCollector.translateToLocal("keybind.inventoryswap");

            swapKey = (KeyBinding) Arrays.stream(Minecraft.getMinecraft().gameSettings.keyBindings)
                    .filter(keyBind -> keyBind.getKeyDescription().equals(description))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e) {
            swapKey = null;
        }

        if (swapKey != null && swapKey.getKeyCode() == key) {
            PacketHandler.INSTANCE.sendToServer(new DWSInventorySwapPacket(this.mc.thePlayer));
        }
    }
}
