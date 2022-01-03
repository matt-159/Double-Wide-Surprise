package com.github.matt159.putin;

import baubles.client.gui.GuiEvents;
import com.github.matt159.putin.inventory.ContainerPutin;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.eventhandler.IEventListener;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import tconstruct.client.tabs.TabRegistry;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

public class CommonProxy implements IGuiHandler {

    // preInit "Run before anything else. Read your config, create blocks, items,
    // etc, and register them with the GameRegistry."
    public void preInit(FMLPreInitializationEvent event) 	{
        Config.syncronizeConfiguration(event.getSuggestedConfigurationFile());
        
        Putin.info(Config.greeting);
        Putin.info("I am " + Tags.MODNAME + " at version " + Tags.VERSION + " and group name " + Tags.GROUPNAME);
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes."
    public void init(FMLInitializationEvent event) {
        Config.isBaublesLoaded = Loader.isModLoaded("Baubles");
        Config.isTravellersGearLoaded = Loader.isModLoaded("TravellersGear");
        Config.isTinkersLoaded = Loader.isModLoaded("TConstruct");
        Config.isGalacticraftLoaded = Loader.isModLoaded("GalacticraftCore");
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

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 1:
                return new ContainerPutin(player.inventory, !world.isRemote, player);
            default:
                return null;
        }
    }

    public void registerHandlers() {}

    public void registerKeyBindings() {}
}