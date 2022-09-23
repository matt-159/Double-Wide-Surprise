package com.github.thebrochacho.dws.mixin.mixins.common.dws.baubles;

import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import com.github.thebrochacho.dws.interfaces.dws.IAddsBaubleSlots;
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

import static com.github.thebrochacho.dws.inventory.slots.SlotDWS.SlotType.*;

@Mixin(ContainerPlayer.class)
public abstract class ContainerPlayerMixin extends Container implements IAddsBaubleSlots {
    @Shadow @Final
    private EntityPlayer thePlayer;

    private IInventory baublesAccessories = null;

    private static int BAUBLES_SLOT_START = -1;

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

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    public void injectBaubleSlots(InventoryPlayer inventoryPlayer, boolean isLocalWorld, EntityPlayer player, CallbackInfo ci) {
        if (BAUBLES_SLOT_START == -1) {
            BAUBLES_SLOT_START = this.inventorySlots.size();
        }

        int xOffset = SlotLayoutManager.getXOffset(SlotLayoutManager.Mods.Baubles);

        baublesAccessories = new InventoryBaubles(player);
        ((InventoryBaubles) baublesAccessories).setEventHandler(this);

        if (!player.worldObj.isRemote) {
            ((InventoryBaubles) baublesAccessories).stackList = PlayerHandler.getPlayerBaubles(player).stackList;
        }

        this.addSlotToContainer(new SlotDWS(baublesAccessories, 0, xOffset, 8 + 0 * 18, player, BAUBLE_AMULET));
        this.addSlotToContainer(new SlotDWS(baublesAccessories, 1, xOffset, 8 + 1 * 18, player, BAUBLE_RING));
        this.addSlotToContainer(new SlotDWS(baublesAccessories, 2, xOffset, 8 + 2 * 18, player, BAUBLE_RING));
        this.addSlotToContainer(new SlotDWS(baublesAccessories, 3, xOffset, 8 + 3 * 18, player, BAUBLE_BELT));
    }

    @Inject(method = "onContainerClosed",
            at = @At("RETURN"),
            require = 1)
    private void injectBaublesOnContainerClosed(EntityPlayer player, CallbackInfo ci) {
        if (ModCompat.isBaublesPresent() && !player.worldObj.isRemote) {
            PlayerHandler.setPlayerBaubles(player, (InventoryBaubles) this.baublesAccessories);
        }
    }
}
