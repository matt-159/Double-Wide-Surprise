package com.github.matt159.dws.events;

import com.github.matt159.dws.network.DWSInventorySwapPacket;
import com.github.matt159.dws.network.PacketHandler;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class DWSSwapKeyHandler {
    public KeyBinding swapKey = new KeyBinding(StatCollector.translateToLocal("keybind.inventoryswap"),
                                               Keyboard.KEY_H,
                                               "key.categories.inventory");

    public DWSSwapKeyHandler() {
        ClientRegistry.registerKeyBinding(swapKey);
        FMLCommonHandler.instance().bus().register(this);
    }

    // Have to do it like this so that key presses inside a gui get captured
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START &&
            swapKey.isPressed() &&
            FMLClientHandler.instance().getClient().inGameHasFocus) {
                PacketHandler.INSTANCE.sendToServer(new DWSInventorySwapPacket(false));
        }
    }
}
