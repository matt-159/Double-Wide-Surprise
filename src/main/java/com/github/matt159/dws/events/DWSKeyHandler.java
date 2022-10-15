package com.github.matt159.dws.events;

import com.github.matt159.dws.network.DWSInventorySwapPacket;
import com.github.matt159.dws.network.PacketHandler;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

public class DWSKeyHandler {

    @SideOnly(Side.CLIENT)
    private static int swapCooldown = 0;

    public KeyBinding swapKey = new KeyBinding(StatCollector.translateToLocal("keybind.inventoryswap"),
                                               Keyboard.KEY_H,
                                               "key.categories.inventory");

    public DWSKeyHandler() {
        ClientRegistry.registerKeyBinding(swapKey);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == Side.SERVER) return;
        if (event.phase == TickEvent.Phase.START) {
            if (swapCooldown > 0) { swapCooldown--; }

            if (swapKey.getIsKeyPressed() &&
                    FMLClientHandler.instance().getClient().inGameHasFocus &&
                    swapCooldown == 0) {
                PacketHandler.INSTANCE.sendToServer(new DWSInventorySwapPacket(false));

                swapCooldown += 5; //ticks
            }
        }
    }
}
