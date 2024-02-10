package com.github.matt159.dws.proxy;

import baubles.client.gui.GuiEvents;
import com.github.matt159.dws.DoubleWideSurprise;
import com.github.matt159.dws.Tags;
import com.github.matt159.dws.events.PlayerOpenContainerEventHandler;
import com.github.matt159.dws.inventory.slots.SlotType;
import com.github.matt159.dws.network.PacketHandler;
import tconstruct.client.tabs.TabRegistry;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.IEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

import static com.github.matt159.dws.util.ModCompat.isBaublesPresent;
import static com.github.matt159.dws.util.ModCompat.isTinkersConstructPresent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        DoubleWideSurprise.info("I am " + Tags.MODNAME + " at version " + Tags.VERSION + " and group name " + Tags.GROUPNAME);

        PacketHandler.init();
    }

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new PlayerOpenContainerEventHandler());
    }

    public void postInit(FMLPostInitializationEvent event) {
        this.disableOtherInventoryButtons();
    }

    @SuppressWarnings("unchecked")
    public void disableOtherInventoryButtons() {
        try {
            Field f = MinecraftForge.EVENT_BUS.getClass().getDeclaredField("listeners");
            f.setAccessible(true);

            ConcurrentHashMap<Object, ArrayList<IEventListener>> listeners = (ConcurrentHashMap<Object, ArrayList<IEventListener>>) f.get(MinecraftForge.EVENT_BUS);

            Enumeration<Object> keys = listeners.keys();
            while (keys.hasMoreElements()) {
                Object key = keys.nextElement();

                // Stop baubles ring button from rendering
                if (isBaublesPresent() && key instanceof GuiEvents)
                    MinecraftForge.EVENT_BUS.unregister(key);

                // Stop tinker's tabs from rendering
                if (isTinkersConstructPresent() && key instanceof TabRegistry)
                    MinecraftForge.EVENT_BUS.unregister(key);
            }
        } catch (Exception e) {
            DoubleWideSurprise.error(e.getMessage());
        }
    }

    public ResourceLocation getSlotHintTexture(SlotType type) {
        return null;
    }
}