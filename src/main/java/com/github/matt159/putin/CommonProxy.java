package com.github.matt159.putin;

import baubles.client.gui.GuiEvents;
import com.github.matt159.putin.gui.Handler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.eventhandler.IEventListener;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import tconstruct.client.tabs.TabRegistry;
import travellersgear.common.util.TGEventHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items,
    // etc, and register them with the GameRegistry."
    public void preInit(FMLPreInitializationEvent event) 	{
        Config.syncronizeConfiguration(event.getSuggestedConfigurationFile());
        
        Putin.info(Config.greeting);
        Putin.info("I am " + Tags.MODNAME + " at version " + Tags.VERSION + " and group name " + Tags.GROUPNAME);
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes."
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(Putin.INSTANCE, new Handler());

        Config.isBaublesLoaded = Loader.isModLoaded("Baubles");
        Config.isTravellersGearLoaded = Loader.isModLoaded("TravellersGear");
        Config.isTinkersLoaded = Loader.isModLoaded("TConstruct");
        Config.isGalacticraftLoaded = Loader.isModLoaded("Galacticraft");
    }

    // postInit "Handle interaction with other mods, complete your setup based on this."
    @SuppressWarnings("unchecked")
    public void postInit(FMLPostInitializationEvent event) {
        try {
            Field f = MinecraftForge.EVENT_BUS.getClass().getDeclaredField("listeners");
            f.setAccessible(true);

            ConcurrentHashMap<Object, ArrayList<IEventListener>> listeners = (ConcurrentHashMap<Object, ArrayList<IEventListener>>) f.get(MinecraftForge.EVENT_BUS);

            Enumeration<Object> keys = listeners.keys();
            while (keys.hasMoreElements()) {
                Object key = keys.nextElement();
                // Stop baubles ring button from rendering
                if (key instanceof GuiEvents) {
                    MinecraftForge.EVENT_BUS.unregister(key);
                }

                // Stop tinker's tabs from rendering
                if (key instanceof TabRegistry) {
                    MinecraftForge.EVENT_BUS.unregister(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}