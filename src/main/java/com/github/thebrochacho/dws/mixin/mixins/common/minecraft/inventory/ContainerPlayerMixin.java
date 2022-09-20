package com.github.thebrochacho.dws.mixin.mixins.common.minecraft.inventory;

import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import com.github.thebrochacho.dws.interfaces.dws.IAddsBaubleSlots;
import com.github.thebrochacho.dws.interfaces.dws.IAddsNullSlots;
import com.github.thebrochacho.dws.interfaces.dws.IAddsTinkersSlots;
import com.github.thebrochacho.dws.inventory.slots.SlotDWS;
import com.github.thebrochacho.dws.util.ModCompat;
import net.minecraft.entity.player.EntityPlayer;
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

import java.util.ArrayList;

import static com.github.thebrochacho.dws.inventory.slots.SlotDWS.SlotType.*;

@Mixin(ContainerPlayer.class)
public abstract class ContainerPlayerMixin extends Container implements IAddsTinkersSlots,
                                                                        IAddsBaubleSlots,
                                                                        IAddsNullSlots {
    private IInventory tinkersAccessories = null;
    private IInventory baublesAccessories = null;

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
//                System.arraycopy(actualPlayerBaubles, 0, playerBaubles, 0, playerBaubles.length);
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
        if (!player.worldObj.isRemote) {
            PlayerHandler.setPlayerBaubles(player, (InventoryBaubles) this.baublesAccessories);
        }
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getNullSlots() {
        return this.nullSlots;
    }
}
