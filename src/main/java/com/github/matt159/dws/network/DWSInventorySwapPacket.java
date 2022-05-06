package com.github.matt159.dws.network;

import com.github.matt159.dws.util.DWSUtil;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class DWSInventorySwapPacket implements IMessage, IMessageHandler<DWSInventorySwapPacket, IMessage> {
    public DWSInventorySwapPacket() {}

    public DWSInventorySwapPacket(EntityPlayer player) {}

    public void fromBytes(ByteBuf buf) {
        //extract extra data from the packet here
    }

    public void toBytes(ByteBuf buf) {
    }

    @Override
    public IMessage onMessage(DWSInventorySwapPacket packet, MessageContext context) {
        //handle incoming packets here. context.side is CLIENT if this packet was RECEIVED by the client, and SERVER if the packet was RECEIVED by the server
        DWSUtil.shiftMainInventory(context.getServerHandler().playerEntity);
        return null;
    }
}