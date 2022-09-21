package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import com.github.matt159.dws.interfaces.dws.*;
import com.github.matt159.dws.inventory.slots.SlotDWS;
import com.github.matt159.dws.util.ModCompat;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tconstruct.armor.player.TPlayerStats;
import travellersgear.TravellersGear;
import travellersgear.api.TravellersGearAPI;
import travellersgear.common.inventory.InventoryTG;
import travellersgear.common.network.MessageNBTSync;

import java.util.ArrayList;

import static com.github.matt159.dws.inventory.slots.SlotDWS.SlotType.*;

@Mixin(ContainerPlayer.class)
public abstract class ContainerPlayerMixin extends Container implements IAddsTinkersSlots,
                                                                        IAddsBaubleSlots,
                                                                        IAddsGCSlots,
                                                                        IAddsTGSlots,
                                                                        IAddsNullSlots {
    private IInventory tinkersAccessories = null;
    private IInventory baublesAccessories = null;

    private IInventory galacticraftAccessories = null;

    private IInventory travellersGearAccessories = null;

    private static int BAUBLES_SLOT_START = -1;
    private static int TINKERS_SLOT_START = -1;
    private static int TG_SLOT_START = -1;
    private static int GC_SLOT_START = -1;

    private final ArrayList<Pair<Integer, Integer>> nullSlots = new ArrayList<>();

    @Shadow @Final
    private EntityPlayer thePlayer;

    @ModifyConstant(method = "<init>",
                    constant = {    @Constant(intValue = 88),
                                    @Constant(intValue = 144)   },
                    require = 1)
    private int modifyCraftingSlotsXOffset(int constant) {
        return constant + 162;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private int modifyColumnCount(int constant) {
        return 18;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 36),
                    require = 4)
    private int modifyHotbarSlotStart(int constant) {
        return 63;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 45),
                    require = 6)
    private int modifyHotbarSlotEnd(int constant) {
        return 81;
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    private void injectAccessorySlots(InventoryPlayer inventoryPlayer, boolean isLocalWorld, EntityPlayer player, CallbackInfo ci) {
        int xOffset = 80;

        if (ModCompat.isBaublesPresent()) {
            if (BAUBLES_SLOT_START == -1) {
                BAUBLES_SLOT_START = this.inventorySlots.size();
            }

            baublesAccessories = new InventoryBaubles(player);
            ((InventoryBaubles) baublesAccessories).setEventHandler(this);

            if (!player.worldObj.isRemote) {
                ((InventoryBaubles) baublesAccessories).stackList = PlayerHandler.getPlayerBaubles(player).stackList;
            }

            this.addSlotToContainer(new SlotDWS(baublesAccessories, 0, xOffset, 8 + 0 * 18, player, BAUBLE_AMULET));
            this.addSlotToContainer(new SlotDWS(baublesAccessories, 1, xOffset, 8 + 1 * 18, player, BAUBLE_RING));
            this.addSlotToContainer(new SlotDWS(baublesAccessories, 2, xOffset, 8 + 2 * 18, player, BAUBLE_RING));
            this.addSlotToContainer(new SlotDWS(baublesAccessories, 3, xOffset, 8 + 3 * 18, player, BAUBLE_BELT));
            xOffset += 18;
        }

        if (ModCompat.isTinkersConstructPresent()) {
            if (TINKERS_SLOT_START == -1) {
                TINKERS_SLOT_START = this.inventorySlots.size();
            }

            if (tinkersAccessories == null) {
                this.tinkersAccessories = TPlayerStats.get(player).armor;
            }

            this.addSlotToContainer(new SlotDWS(tinkersAccessories, 0, xOffset, 8 + 0 * 18, player, TINKERS_MASK));
            this.addSlotToContainer(new SlotDWS(tinkersAccessories, 1, xOffset, 8 + 1 * 18, player, TINKERS_GLOVE));
            this.addSlotToContainer(new SlotDWS(tinkersAccessories, 3, xOffset, 8 + 2 * 18, player, TINKERS_BELT));
            this.addSlotToContainer(new SlotDWS(tinkersAccessories, 2, xOffset, 8 + 3 * 18, player, TINKERS_KNAPSACK));
            xOffset += 18;

            this.addSlotToContainer(new SlotDWS(tinkersAccessories, 6, xOffset, 8 + 0 * 18, player, TINKERS_HEART_RED));
            this.addSlotToContainer(new SlotDWS(tinkersAccessories, 5, xOffset, 8 + 1 * 18, player, TINKERS_HEART_YELLOW));
            this.addSlotToContainer(new SlotDWS(tinkersAccessories, 4, xOffset, 8 + 2 * 18, player, TINKERS_HEART_GREEN));
            nullSlots.add(Pair.of(xOffset, 8 + 3 * 18));
            xOffset += 18;
        }

        if (ModCompat.isGalacticraftPresent()) {
            if (GC_SLOT_START == -1) {
                GC_SLOT_START = this.inventorySlots.size();
            }

            if (galacticraftAccessories == null) {
                if (!player.worldObj.isRemote) {
                    EntityPlayerMP playerMP = PlayerUtil.getPlayerBaseServerFromPlayer(player, false);
                    this.galacticraftAccessories = GCPlayerStats.get(playerMP).extendedInventory;
                } else {
                    this.galacticraftAccessories = ClientProxyCore.dummyInventory;
                }
            }

            if (galacticraftAccessories != null) {
                this.addSlotToContainer(new SlotDWS(galacticraftAccessories, 6, xOffset, 8 + 0 * 18, player, GC_THERMAL_HELM));
                this.addSlotToContainer(new SlotDWS(galacticraftAccessories, 7, xOffset, 8 + 1 * 18, player, GC_THERMAL_CHEST));
                this.addSlotToContainer(new SlotDWS(galacticraftAccessories, 8, xOffset, 8 + 2 * 18, player, GC_THERMAL_LEGS));
                this.addSlotToContainer(new SlotDWS(galacticraftAccessories, 9, xOffset, 8 + 3 * 18, player, GC_THERMAL_BOOTS));
                xOffset += 18;

                this.addSlotToContainer(new SlotDWS(galacticraftAccessories, 4, xOffset, 8 + 0 * 18, player, GC_PARACHUTE));
                this.addSlotToContainer(new SlotDWS(galacticraftAccessories, 0, xOffset, 8 + 1 * 18, player, GC_OXYGEN_MASK));
                this.addSlotToContainer(new SlotDWS(galacticraftAccessories, 2, xOffset, 8 + 2 * 18, player, GC_OXYGEN_TANK));
                nullSlots.add(Pair.of(xOffset, 8 + 3 * 18));
                xOffset += 18;

                this.addSlotToContainer(new SlotDWS(galacticraftAccessories, 5, xOffset, 8 + 0 * 18, player, GC_FREQUENCY_MODULE));
                this.addSlotToContainer(new SlotDWS(galacticraftAccessories, 1, xOffset, 8 + 1 * 18, player, GC_OXYGEN_GEAR));
                this.addSlotToContainer(new SlotDWS(galacticraftAccessories, 3, xOffset, 8 + 2 * 18, player, GC_OXYGEN_TANK));
                nullSlots.add(Pair.of(xOffset, 8 + 3 * 18));
                xOffset += 18;
            }
        }

        if (ModCompat.isTravellersGearPresent()) {
            if (TG_SLOT_START == -1) {
                TG_SLOT_START = this.inventorySlots.size();
            }

            travellersGearAccessories = new InventoryTG(this, player);
            if(!player.worldObj.isRemote) {
                ((InventoryTG) (travellersGearAccessories)).stackList = TravellersGearAPI.getExtendedInventory(player);
            }

            this.addSlotToContainer(new SlotDWS(travellersGearAccessories, 0, xOffset, 8 + 0 * 18, player, TRAVEL_CLOAK));
            this.addSlotToContainer(new SlotDWS(travellersGearAccessories, 1, xOffset, 8 + 1 * 18, player, TRAVEL_PAULDRON));
            this.addSlotToContainer(new SlotDWS(travellersGearAccessories, 2, xOffset, 8 + 2 * 18, player, TRAVEL_VAMBRACE));
            this.addSlotToContainer(new SlotDWS(travellersGearAccessories, 3, xOffset, 8 + 3 * 18, player, TRAVEL_TITLE));
        }
    }

    @Override
    public IInventory getTinkersAccessories() {
        if (ModCompat.isTinkersConstructPresent() && this.tinkersAccessories == null) {

        }

        return this.tinkersAccessories;
    }

    @Override
    public int getTinkersSlotStart() {
        return TINKERS_SLOT_START;
    }

    @Override
    public IInventory getBaublesAccessories() {
        return this.baublesAccessories;
    }

    @Override
    public void setBaublesAccessories(IInventory baublesAccessories) {
        if (ModCompat.isBaublesPresent()) {
            ItemStack[] playerBaubles = ((InventoryBaubles) (this.baublesAccessories)).stackList;
            ItemStack[] actualPlayerBaubles = ((InventoryBaubles) (baublesAccessories)).stackList;

            if (playerBaubles.length != actualPlayerBaubles.length) {
                throw new RuntimeException("What the actual fuck? How?");
            }

            //tie references together on the server, but copy pasta on the client side
            if (!this.thePlayer.worldObj.isRemote) {
                ((InventoryBaubles) (this.baublesAccessories)).stackList = actualPlayerBaubles;
            } else {
                for (int i = 0; i < playerBaubles.length; i++) {
                    if (playerBaubles[i] == null && actualPlayerBaubles[i] != null) {
                        playerBaubles[i] = actualPlayerBaubles[i].copy();
                    }
                }
            }
        }
    }

    @Override
    public int getBaublesSlotStart() {
        return BAUBLES_SLOT_START;
    }

    @Inject(method = "onContainerClosed",
            at = @At("RETURN"),
            require = 1)
    private void injectBaublesOnContainerClosed(EntityPlayer player, CallbackInfo ci) {
        if (ModCompat.isBaublesPresent() && !player.worldObj.isRemote) {
            PlayerHandler.setPlayerBaubles(player, (InventoryBaubles) this.baublesAccessories);
        }
    }

    @Override
    public int getGCSlotStart() {
        return GC_SLOT_START;
    }

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

    @Inject(method = "onContainerClosed",
            at = @At("RETURN"),
            require = 1)
    private void injectTravellersGearOnContainerClosed(EntityPlayer player, CallbackInfo ci) {
        if (ModCompat.isTravellersGearPresent() && !player.worldObj.isRemote) {
            TravellersGearAPI.setExtendedInventory(player, ((InventoryTG) (this.travellersGearAccessories)).stackList);
            TravellersGear.packetHandler.sendToAll(new MessageNBTSync(player));
        }
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getNullSlots() {
        return this.nullSlots;
    }
}
