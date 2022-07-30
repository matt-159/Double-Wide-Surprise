package com.github.matt159.dws.mixins.client.minecraft;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.network.DWSInventorySwapPacket;
import com.github.matt159.dws.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.StatCollector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Mixin(GuiContainer.class)
public abstract class GuiContainerMixin extends GuiScreen {

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifyDefaultXSize(int constant) {
        return (this instanceof IDWSGui) ? 338 : 176;
    }

    @Inject(method = "keyTyped",
            at = @At(value = "TAIL"),
            require = 1)
    private void injectDWSHotkey(char p_73869_1_, int key, CallbackInfo ci) {
        KeyBinding swapKey;
        try {
            String description = StatCollector.translateToLocal("keybind.inventoryswap");

            swapKey = Arrays.stream(Minecraft.getMinecraft().gameSettings.keyBindings)
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
