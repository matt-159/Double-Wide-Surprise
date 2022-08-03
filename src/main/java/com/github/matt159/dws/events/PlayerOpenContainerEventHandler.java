package com.github.matt159.dws.events;

import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import com.github.matt159.dws.inventory.ContainerDWS;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.inventory.Container;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;

public class PlayerOpenContainerEventHandler {
    @SubscribeEvent
    public void onPlayerOpenContainer(PlayerOpenContainerEvent event) {
        Container container = event.entityPlayer.inventoryContainer;
        InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(event.entityPlayer);

        if (container instanceof ContainerDWS) {
            ((ContainerDWS) container).baubles.stackList = baubles.stackList;
        }
    }
}
