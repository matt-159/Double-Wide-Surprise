package com.github.matt159.dws.proxy;

import com.github.matt159.dws.inventory.slots.SlotType;
import com.github.matt159.dws.registry.Keybindings;

import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
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
    public ResourceLocation getSlotHintTexture(SlotType type) {
        return type.getSlotHintTexture();
    }
}