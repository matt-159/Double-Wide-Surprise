package com.github.matt159.dws.registry;

import com.github.matt159.dws.config.DWSConfig;
import events.BaublesTooltipEventHandler;

import net.minecraftforge.common.MinecraftForge;

public class Events {
    public static void registerClientEvents() {
        if (DWSConfig.Slots.slotOverride) {
            MinecraftForge.EVENT_BUS.register(new BaublesTooltipEventHandler());
        }
    }
}
