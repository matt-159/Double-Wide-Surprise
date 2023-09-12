package com.github.matt159.dws.mixin.mixins.common.dws.tinkersconstruct;

import com.github.matt159.dws.interfaces.dws.IAddsTinkersSlots;
import com.github.matt159.dws.inventory.slots.SlotDWS;
import com.github.matt159.dws.inventory.slots.SlotType;
import com.github.matt159.dws.inventory.slots.compat.SlotTinkersCompat;
import com.github.matt159.dws.util.SlotLayoutManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tconstruct.armor.player.TPlayerStats;

@Mixin(ContainerPlayer.class)
public abstract class ContainerPlayerMixin extends Container implements IAddsTinkersSlots {

    private IInventory tinkersAccessories = null;

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    private void injectTinkersSlots(InventoryPlayer inventoryPlayer, boolean isLocalWorld, EntityPlayer player, CallbackInfo ci) {
        if (SlotLayoutManager.FIRST_TINKERS_SLOT == Integer.MAX_VALUE) {
            SlotLayoutManager.FIRST_TINKERS_SLOT = this.inventorySlots.size();
        }

        int xOffset = SlotLayoutManager.getXOffset(SlotLayoutManager.Mods.TinkersConstruct);

        if (tinkersAccessories == null) {
            this.tinkersAccessories = TPlayerStats.get(player).armor;
        }

        this.addSlotToContainer(new SlotTinkersCompat(tinkersAccessories,
                                                      0,
                                                      xOffset,
                                                      8 + 0 * 18,
                                                      SlotType.TINKERS_MASK));
        this.addSlotToContainer(new SlotTinkersCompat(tinkersAccessories,
                                                      1,
                                                      xOffset,
                                                      8 + 1 * 18,
                                                      SlotType.TINKERS_GLOVE));
        this.addSlotToContainer(new SlotTinkersCompat(tinkersAccessories,
                                                      3,
                                                      xOffset,
                                                      8 + 2 * 18,
                                                      SlotType.TINKERS_BELT));
        this.addSlotToContainer(new SlotTinkersCompat(tinkersAccessories,
                                                      2,
                                                      xOffset,
                                                      8 + 3 * 18,
                                                      SlotType.TINKERS_KNAPSACK));
        xOffset += 18;

        this.addSlotToContainer(new SlotTinkersCompat(tinkersAccessories,
                                                      6,
                                                      xOffset,
                                                      8 + 0 * 18,
                                                      SlotType.TINKERS_HEART_RED));
        this.addSlotToContainer(new SlotTinkersCompat(tinkersAccessories,
                                                      5,
                                                      xOffset,
                                                      8 + 1 * 18,
                                                      SlotType.TINKERS_HEART_YELLOW));
        this.addSlotToContainer(new SlotTinkersCompat(tinkersAccessories,
                                                      4,
                                                      xOffset,
                                                      8 + 2 * 18,
                                                      SlotType.TINKERS_HEART_GREEN));
    }
}
