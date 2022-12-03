package com.github.matt159.dws.network;

import com.github.matt159.dws.util.DWSUtil;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class DWSInventorySwapPacket implements IMessage, IMessageHandler<DWSInventorySwapPacket, IMessage> {
    boolean isSmallGui;

    public DWSInventorySwapPacket() {
        this(false);
    }

    public DWSInventorySwapPacket(boolean isSmallGui) {
        this.isSmallGui = isSmallGui;
    }

    public void fromBytes(ByteBuf buf) {
       this.isSmallGui = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.isSmallGui);
    }

    @Override
    public IMessage onMessage(DWSInventorySwapPacket packet, MessageContext context) {
        DWSUtil.shiftMainInventory(context.getServerHandler().playerEntity, packet.isSmallGui);
        return null;
    }
}