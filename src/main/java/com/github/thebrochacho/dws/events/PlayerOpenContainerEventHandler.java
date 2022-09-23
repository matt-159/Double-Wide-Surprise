package com.github.thebrochacho.dws.events;

import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import com.github.thebrochacho.dws.interfaces.dws.IAddsBaubleSlots;
import com.github.thebrochacho.dws.util.ModCompat;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.inventory.Container;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;

public class PlayerOpenContainerEventHandler {
    /*  The InventoryBaubles object inside ContainerDWS is likely to get constructed prior to the player's baubles being
     *  loaded from file. This forces synchronization of the player's baubles
     */
    @SubscribeEvent
    public void onPlayerOpenContainer(PlayerOpenContainerEvent event) {
        if (!event.entityPlayer.worldObj.isRemote) {
            if (ModCompat.isBaublesPresent()) {
                Container container = event.entityPlayer.inventoryContainer;
                InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(event.entityPlayer);

                ((IAddsBaubleSlots) container).setBaublesAccessories(baubles);

                container.detectAndSendChanges();
            }

//            if (ModCompat.isTravellersGearPresent()) {
//                Container container = event.entityPlayer.inventoryContainer;
//                ItemStack[] playerTGAccessories = TravellersGearAPI.getExtendedInventory(event.entityPlayer);
//
//                ((IAddsTGSlots) container).setTravellersGearAccessories(playerTGAccessories);
//            }
        }
    }
}
