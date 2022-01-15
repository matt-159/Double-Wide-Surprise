package com.github.thebrochacho.putin.network;

import com.github.thebrochacho.putin.util.PutinUtil;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class PutinInventorySwapPacket implements IMessage, IMessageHandler<PutinInventorySwapPacket, IMessage> {
    public PutinInventorySwapPacket() {}

    public PutinInventorySwapPacket(EntityPlayer player) {}

    public void fromBytes(ByteBuf buf) {
        //extract extra data from the packet here
    }

    public void toBytes(ByteBuf buf) {
    }

    @Override
    public IMessage onMessage(PutinInventorySwapPacket packet, MessageContext context) {
        //handle incoming packets here. context.side is CLIENT if this packet was RECEIVED by the client, and SERVER if the packet was RECEIVED by the server
        PutinUtil.shiftMainInventory(context.getServerHandler().playerEntity);
        return null;
    }
}