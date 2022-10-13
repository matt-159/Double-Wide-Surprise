package com.github.matt159.dws.network;

import com.github.matt159.dws.Tags;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Tags.MODID);

    public static void init() {
        INSTANCE.registerMessage(DWSInventorySwapPacket.class, DWSInventorySwapPacket.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(DWSInventorySwapPacket.class, DWSInventorySwapPacket.class, 1, Side.SERVER);

        INSTANCE.registerMessage(DWSRearrangePlayerInventoryPacket.class, DWSRearrangePlayerInventoryPacket.class, 2, Side.CLIENT);
        INSTANCE.registerMessage(DWSRearrangePlayerInventoryPacket.class, DWSRearrangePlayerInventoryPacket.class, 3, Side.SERVER);
    }
}