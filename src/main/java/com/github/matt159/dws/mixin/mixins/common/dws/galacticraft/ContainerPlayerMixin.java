package com.github.matt159.dws.mixin.mixins.common.dws.galacticraft;

import com.github.matt159.dws.interfaces.dws.IAddsGCSlots;
import com.github.matt159.dws.inventory.slots.SlotDWS;
import com.github.matt159.dws.inventory.slots.SlotType;
import com.github.matt159.dws.inventory.slots.compat.SlotGalacticraftCompat;
import com.github.matt159.dws.util.SlotLayoutManager;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerPlayer.class)
public abstract class ContainerPlayerMixin extends Container implements IAddsGCSlots {

    private IInventory galacticraftAccessories = null;

    private static int GC_SLOT_START = -1;

    @Override
    public int getGCSlotStart() {
        return GC_SLOT_START;
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    private void injectGalacticraftSlots(InventoryPlayer inventoryPlayer, boolean isLocalWorld, EntityPlayer player, CallbackInfo ci) {
        if (GC_SLOT_START == -1) {
            GC_SLOT_START = this.inventorySlots.size();
        }

        int xOffset = SlotLayoutManager.getXOffset(SlotLayoutManager.Mods.Galacticraft);

        if (galacticraftAccessories == null) {
            if (!player.worldObj.isRemote) {
                EntityPlayerMP playerMP = PlayerUtil.getPlayerBaseServerFromPlayer(player, false);
                this.galacticraftAccessories = GCPlayerStats.get(playerMP).extendedInventory;
            } else {
                this.galacticraftAccessories = ClientProxyCore.dummyInventory;
            }
        }

        this.addSlotToContainer(new SlotGalacticraftCompat(galacticraftAccessories,
                                                           6, xOffset,
                                                           8 + 0 * 18,
                                                           SlotType.GC_THERMAL_HELM));
        this.addSlotToContainer(new SlotGalacticraftCompat(galacticraftAccessories,
                                                           7, xOffset,
                                                           8 + 1 * 18,
                                                           SlotType.GC_THERMAL_CHEST));
        this.addSlotToContainer(new SlotGalacticraftCompat(galacticraftAccessories,
                                                            8, xOffset,
                                                            8 + 2 * 18,
                                                            SlotType.GC_THERMAL_LEGS));
        this.addSlotToContainer(new SlotGalacticraftCompat(galacticraftAccessories,
                                                            9, xOffset,
                                                            8 + 3 * 18,
                                                            SlotType.GC_THERMAL_BOOTS));
        xOffset += 18;

        this.addSlotToContainer(new SlotGalacticraftCompat(galacticraftAccessories,
                                                           4,
                                                           xOffset,
                                                           8 + 0 * 18,
                                                           SlotType.GC_PARACHUTE));
        this.addSlotToContainer(new SlotGalacticraftCompat(galacticraftAccessories,
                                                           0,
                                                           xOffset,
                                                           8 + 1 * 18,
                                                           SlotType.GC_OXYGEN_MASK));
        this.addSlotToContainer(new SlotGalacticraftCompat(galacticraftAccessories,
                                                           2,
                                                           xOffset,
                                                           8 + 2 * 18,
                                                           SlotType.GC_OXYGEN_TANK));
        xOffset += 18;

        this.addSlotToContainer(new SlotGalacticraftCompat(galacticraftAccessories,
                                                           5,
                                                           xOffset,
                                                           8 + 0 * 18,
                                                           SlotType.GC_FREQUENCY_MODULE));
        this.addSlotToContainer(new SlotGalacticraftCompat(galacticraftAccessories,
                                                           1,
                                                           xOffset,
                                                           8 + 1 * 18,
                                                           SlotType.GC_OXYGEN_GEAR));
        this.addSlotToContainer(new SlotGalacticraftCompat(galacticraftAccessories,
                                                           3,
                                                           xOffset,
                                                           8 + 2 * 18,
                                                           SlotType.GC_OXYGEN_TANK));
//        nullSlots.add(Pair.of(xOffset, 8 + 3 * 18));
    }
}
