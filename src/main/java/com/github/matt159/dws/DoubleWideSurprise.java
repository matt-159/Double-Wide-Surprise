package com.github.matt159.dws;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

import com.github.matt159.dws.config.DWSConfig;
import com.github.matt159.dws.proxy.CommonProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.7.10]",
     guiFactory = Tags.GROUPNAME + ".client.gui.config.DWSGuiFactory",
     dependencies =  "after:Baubles; " +
                     "after:Baubles|Expanded; " +
                     "after:TravellersGear; " +
                     "after:GalacticraftCore; " +
                     "after:TConstruct; " +
                     "after:IronChest; " +
                     "after:gregtech; " +
                     "after:appliedenergistics2-core; " +
                     "after:Forestry; " +
                     "after:StorageDrawers; " +
                     "after:inventorytweaks; " +
                     "after:endercore; ")
public class DoubleWideSurprise {
    private static Logger LOG = LogManager.getLogger(Tags.MODID);

    @Mod.Instance(Tags.MODID)
    public static DoubleWideSurprise INSTANCE;

    @SidedProxy(clientSide= Tags.GROUPNAME + ".proxy.ClientProxy",
                serverSide=Tags.GROUPNAME + ".proxy.CommonProxy")
    public static CommonProxy proxy;

    static {
        DWSConfig.poke();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    public static void debug(String message) {
        LOG.debug(message);
    }

    public static void info(String message) {
        LOG.info(message);
    }

    public static void warn(String message) {
        LOG.warn(message);
    }

    public static void error(String message) {
        LOG.error(message);
    }
}