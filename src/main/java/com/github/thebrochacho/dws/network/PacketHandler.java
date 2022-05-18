package com.github.thebrochacho.dws.network;

import com.github.thebrochacho.dws.Tags;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Tags.MODID);

    public static void init() {
        INSTANCE.registerMessage(DWSInventorySwapPacket.class, DWSInventorySwapPacket.class, 0, Side.SERVER);
        INSTANCE.registerMessage(DWSInventorySwapPacket.class, DWSInventorySwapPacket.class, 0, Side.CLIENT);
    }
}