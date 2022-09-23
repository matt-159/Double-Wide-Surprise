package com.github.thebrochacho.dws.mixin.mixins.common.dws.tinkersconstruct;

import com.github.thebrochacho.dws.interfaces.dws.IAddsTinkersSlots;
import com.github.thebrochacho.dws.inventory.slots.SlotDWS;
import com.github.thebrochacho.dws.util.SlotLayoutManager;
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

import static com.github.thebrochacho.dws.inventory.slots.SlotDWS.SlotType.*;

@Mixin(ContainerPlayer.class)
public abstract class ContainerPlayerMixin extends Container implements IAddsTinkersSlots {

    private IInventory tinkersAccessories = null;

    private static int TINKERS_SLOT_START = -1;

    @Override
    public IInventory getTinkersAccessories() {
        return this.tinkersAccessories;
    }

    @Override
    public int getTinkersSlotStart() {
        return TINKERS_SLOT_START;
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    private void injectTinkersSlots(InventoryPlayer inventoryPlayer, boolean isLocalWorld, EntityPlayer player, CallbackInfo ci) {
        if (TINKERS_SLOT_START == -1) {
            TINKERS_SLOT_START = this.inventorySlots.size();
        }

        int xOffset = SlotLayoutManager.getXOffset(SlotLayoutManager.Mods.TinkersConstruct);

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
    }
}
