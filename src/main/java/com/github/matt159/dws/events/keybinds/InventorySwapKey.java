package com.github.matt159.dws.events.keybinds;

import com.github.matt159.dws.Tags;
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
public class InventorySwapKey extends KeyHandler {
    public InventorySwapKey() {
        super(StatCollector.translateToLocal("dws.keybind.inventoryswap.name"),
              Keyboard.KEY_H,
              Tags.MODNAME,
              new SwapKeyCallback());
    }

    private static class SwapKeyCallback implements KeyHandler.CallBack {
        @Override
        public void onPress() {
            PacketHandler.INSTANCE.sendToServer(new DWSInventorySwapPacket(false));
        }

        @Override
        public void tick() {

        }
    }
}
