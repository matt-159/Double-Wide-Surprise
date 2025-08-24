package com.github.matt159.dws.mixin.mixins.common.dws.baubles;

import baubles.api.expanded.BaubleExpandedSlots;
import baubles.common.container.InventoryBaubles;
import baubles.common.container.SlotBauble;
import baubles.common.lib.PlayerHandler;
import com.github.matt159.dws.config.DWSConfig;
import com.github.matt159.dws.interfaces.dws.IBaubleManager;
import com.github.matt159.dws.inventory.slots.MixAndMatchBaublesSlot;
import com.github.matt159.dws.inventory.slots.SlotType;
import com.github.matt159.dws.inventory.slots.compat.SlotBaublesCompat;
import com.github.matt159.dws.util.ModCompat;
import com.github.matt159.dws.util.ReflectedModSupport;
import com.github.matt159.dws.util.SlotLayoutManager;
import lombok.val;
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

import static com.github.matt159.dws.util.SlotLayoutManager.Mods;

@Mixin(ContainerPlayer.class)
public abstract class ContainerPlayerMixin extends Container implements IBaubleManager {
    @Shadow @Final
    private EntityPlayer thePlayer;

    private IInventory baublesAccessories = null;

    @Override
    public void setBaublesAccessories(IInventory baublesAccessories) {
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

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    public void injectBaubleSlots(InventoryPlayer inventoryPlayer, boolean isLocalWorld, EntityPlayer player, CallbackInfo ci) {
        if (SlotLayoutManager.FIRST_BAUBLES_SLOT == Integer.MAX_VALUE) {
            SlotLayoutManager.FIRST_BAUBLES_SLOT = this.inventorySlots.size();
        }

        val mod = ModCompat.isBaublesExpandedPresent() ? Mods.BaublesExpanded : Mods.Baubles;
        int xOffset = SlotLayoutManager.getXOffset(mod);

        baublesAccessories = new InventoryBaubles(player);
        ((InventoryBaubles) baublesAccessories).setEventHandler(this);

        if (!player.worldObj.isRemote) {
            ((InventoryBaubles) baublesAccessories).stackList = PlayerHandler.getPlayerBaubles(player).stackList;
        }

        if (DWSConfig.Slots.slotOverride && ModCompat.isBaublesExpandedPresent()) {
            for (int index = 0; index < BaubleExpandedSlots.slotLimit; ++index) {
                this.addSlotToContainer(new MixAndMatchBaublesSlot(player,
                                                                   baublesAccessories,
                                                                   index,
                                                                   xOffset + 18 * (index / 4),
                                                                   8 + 18 * (index % 4)));
            }
        } else {
            if (ModCompat.isBaublesExpandedPresent()) {
                for (int index = 0; index < BaubleExpandedSlots.slotLimit; ++index) {
                    String slotType = ReflectedModSupport.BaublesExpandedSlots_getSlotType(index);

                    if (ReflectedModSupport.BaublesConfig_showUnusedSlots || !slotType.equals("unknown")) {
                        this.addSlotToContainer(new SlotBauble(baublesAccessories,
                                                               slotType,
                                                               index,
                                                               xOffset + 18 * (index / 4),
                                                               8 + 18 * (index % 4)));
                    }
                }
            } else {
                this.addSlotToContainer(new SlotBaublesCompat(baublesAccessories,
                                                              0, xOffset,
                                                              8 + 0 * 18,
                                                              player,
                                                              SlotType.BAUBLE_AMULET));

                this.addSlotToContainer(new SlotBaublesCompat(baublesAccessories,
                                                              1, xOffset,
                                                              8 + 1 * 18,
                                                              player,
                                                              SlotType.BAUBLE_RING));

                this.addSlotToContainer(new SlotBaublesCompat(baublesAccessories,
                                                              2, xOffset,
                                                              8 + 2 * 18,
                                                              player,
                                                              SlotType.BAUBLE_RING));

                this.addSlotToContainer(new SlotBaublesCompat(baublesAccessories,
                                                              3,
                                                              xOffset,
                                                              8 + 3 * 18,
                                                              player,
                                                              SlotType.BAUBLE_BELT));
            }
        }
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
