package com.github.matt159.dws;

import baubles.client.gui.GuiEvents;
import com.github.matt159.dws.events.PlayerOpenContainerEventHandler;
import com.github.matt159.dws.inventory.slots.SlotType;
import com.github.matt159.dws.network.PacketHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.eventhandler.IEventListener;
import net.minecraftforge.common.MinecraftForge;
import tconstruct.client.tabs.TabRegistry;

import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

import static com.github.matt159.dws.util.ModCompat.isBaublesPresent;
import static com.github.matt159.dws.util.ModCompat.isTinkersConstructPresent;

public class CommonProxy {
    // preInit "Run before anything else. Read your config, create blocks, items,
    // etc, and register them with the GameRegistry."
    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        DoubleWideSurprise.info("I am " + Tags.MODNAME + " at version " + Tags.VERSION + " and group name " + Tags.GROUPNAME);

        PacketHandler.init();
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes."
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new PlayerOpenContainerEventHandler());
    }

    // postInit "Handle interaction with other mods, complete your setup based on this."
    @SuppressWarnings("unchecked")
    public void postInit(FMLPostInitializationEvent event) {
        disableOtherInventoryButtons();
    }

    public void serverAboutToStart(FMLServerAboutToStartEvent event) {

    }

    // register server commands in this event handler
    public void serverStarting(FMLServerStartingEvent event) {

    }

    public void serverStarted(FMLServerStartedEvent event) {

    }

    public void serverStopping(FMLServerStoppingEvent event) {

    }

    public void serverStopped(FMLServerStoppedEvent event) {

    }

    public void registerHandlers() {
    }

    public void registerKeyBindings() {
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
            e.printStackTrace();
        }
    }

    public ResourceLocation getSlotHintTexture(SlotType type) {
        return null;
    }
}