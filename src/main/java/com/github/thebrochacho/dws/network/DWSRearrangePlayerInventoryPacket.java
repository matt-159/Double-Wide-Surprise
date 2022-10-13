package com.github.thebrochacho.dws.network;

import com.github.thebrochacho.dws.util.DWSUtil;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class DWSRearrangePlayerInventoryPacket implements IMessage, IMessageHandler<DWSRearrangePlayerInventoryPacket, IMessage> {
    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public IMessage onMessage(DWSRearrangePlayerInventoryPacket message, MessageContext ctx) {
        DWSUtil.RearrangeInventoryForFallback(ctx.getServerHandler().playerEntity);
        return null;
    }
}
