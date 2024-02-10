package com.github.matt159.dws.proxy;

import com.github.matt159.dws.events.keybinds.DWSSwapKeyHandler;
import com.github.matt159.dws.inventory.slots.SlotType;
import com.github.matt159.dws.registry.Keybindings;

import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

public class ClientProxy extends CommonProxy {
    public DWSSwapKeyHandler keyHandler;

    public void preInit(FMLPreInitializationEvent event) 	{
        super.preInit(event);
    }

    public void init(FMLInitializationEvent event) {
        super.init(event);

        Keybindings.register();
    }

    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public void registerKeyBindings() {
        keyHandler = new DWSSwapKeyHandler();
    }

    @Override
    public ResourceLocation getSlotHintTexture(SlotType type) {
        return type.getSlotHintTexture();
    }
}