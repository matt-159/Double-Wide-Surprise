package com.github.matt159.putin.network;

import com.github.matt159.putin.Tags;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Tags.MODID);

    public static void init() {
        INSTANCE.registerMessage(PutinInventorySwapPacket.class, PutinInventorySwapPacket.class, 0, Side.SERVER);
        INSTANCE.registerMessage(PutinInventorySwapPacket.class, PutinInventorySwapPacket.class, 0, Side.CLIENT);
    }
}