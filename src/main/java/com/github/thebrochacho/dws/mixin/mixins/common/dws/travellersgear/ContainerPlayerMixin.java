package com.github.thebrochacho.dws.mixin.mixins.common.dws.travellersgear;

import com.github.thebrochacho.dws.interfaces.dws.IAddsTGSlots;
import com.github.thebrochacho.dws.inventory.slots.SlotDWS;
import com.github.thebrochacho.dws.util.ModCompat;
import com.github.thebrochacho.dws.util.SlotLayoutManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import travellersgear.TravellersGear;
import travellersgear.api.TravellersGearAPI;
import travellersgear.common.inventory.InventoryTG;
import travellersgear.common.network.MessageNBTSync;

import static com.github.thebrochacho.dws.inventory.slots.SlotDWS.SlotType.*;
import static com.github.thebrochacho.dws.inventory.slots.SlotDWS.SlotType.TRAVEL_TITLE;

@Mixin(ContainerPlayer.class)
public abstract class ContainerPlayerMixin extends Container implements IAddsTGSlots {

    @Shadow @Final
    private EntityPlayer thePlayer;

    private IInventory travellersGearAccessories = null;

    private static int TG_SLOT_START = -1;

    @Override
    public int getTGSlotStart() {
        return TG_SLOT_START;
    }

    @Override
    public ItemStack[] getTravellersAccessoriesItemStacks() {
        return ((InventoryTG) (this.travellersGearAccessories)).stackList;
    }

    @Override
    public void setTravellersGearAccessories(ItemStack[] actualPlayerTGAccessories) {
        if (ModCompat.isTravellersGearPresent()) {
            ItemStack[] playerTGAccessories = ((InventoryTG) (this.travellersGearAccessories)).stackList;

            if (((InventoryTG) (this.travellersGearAccessories)).stackList.length != actualPlayerTGAccessories.length) {
                throw new RuntimeException("What the actual fuck? How?");
            }

            if (!this.thePlayer.worldObj.isRemote) {
                ((InventoryTG) (this.travellersGearAccessories)).stackList = actualPlayerTGAccessories;
            } else {
                for (int i = 0; i < playerTGAccessories.length; i++) {
                    if (playerTGAccessories[i] == null && actualPlayerTGAccessories[i] != null) {
                        ((InventoryTG) (this.travellersGearAccessories)).stackList[i] = actualPlayerTGAccessories[i].copy();
                    }
                }
            }
        }
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    private void injectTravellersGearSlots(InventoryPlayer inventoryPlayer, boolean isLocalWorld, EntityPlayer player, CallbackInfo ci) {
        if (TG_SLOT_START == -1) {
            TG_SLOT_START = this.inventorySlots.size();
        }

        int xOffset = SlotLayoutManager.getXOffset(SlotLayoutManager.Mods.TravellersGear);

        travellersGearAccessories = new InventoryTG(this, player);
        if(!player.worldObj.isRemote) {
            ((InventoryTG) (travellersGearAccessories)).stackList = TravellersGearAPI.getExtendedInventory(player);
        }

        this.addSlotToContainer(new SlotDWS(travellersGearAccessories, 0, xOffset, 8 + 0 * 18, player, TRAVEL_CLOAK));
        this.addSlotToContainer(new SlotDWS(travellersGearAccessories, 1, xOffset, 8 + 1 * 18, player, TRAVEL_PAULDRON));
        this.addSlotToContainer(new SlotDWS(travellersGearAccessories, 2, xOffset, 8 + 2 * 18, player, TRAVEL_VAMBRACE));
        this.addSlotToContainer(new SlotDWS(travellersGearAccessories, 3, xOffset, 8 + 3 * 18, player, TRAVEL_TITLE));
    }

    @Inject(method = "onContainerClosed",
            at = @At("RETURN"),
            require = 1)
    private void injectTravellersGearOnContainerClosed(EntityPlayer player, CallbackInfo ci) {
        if (ModCompat.isTravellersGearPresent() && !player.worldObj.isRemote) {
            TravellersGearAPI.setExtendedInventory(player, ((InventoryTG) (this.travellersGearAccessories)).stackList);
            TravellersGear.packetHandler.sendToAll(new MessageNBTSync(player));
        }
    }
}
