package com.github.matt159.dws.mixin.mixins.client.minecraft.gui;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.interfaces.dws.IAddsBaubleSlots;
import com.github.matt159.dws.interfaces.dws.IAddsGCSlots;
import com.github.matt159.dws.interfaces.dws.IAddsTGSlots;
import com.github.matt159.dws.interfaces.dws.IAddsTinkersSlots;
import com.github.matt159.dws.util.ModCompat;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiInventory.class)
public abstract class GuiInventoryMixin extends InventoryEffectRenderer implements IDWSGui {
    public GuiInventoryMixin(Container container) {
        super(container);
    }

    @Inject(method = "drawGuiContainerBackgroundLayer",
            at = @At(   value = "INVOKE",
                        target = "Lnet/minecraft/client/gui/inventory/GuiInventory;drawTexturedModalRect(IIIIII)V",
                        shift = At.Shift.AFTER),
            require = 1)
    private void injectDrawAccessorySlots(float p_146976_1_, int p_146976_2_, int p_146976_3_, CallbackInfo ci) {
        int tinkersSlotStart = Integer.MAX_VALUE;
        int baublesSlotStart = Integer.MAX_VALUE;
        int tgSlotStart = Integer.MAX_VALUE;
        int gcSlotStart = Integer.MAX_VALUE;

        if (ModCompat.isTinkersConstructPresent() && this.inventorySlots instanceof IAddsTinkersSlots) {
            tinkersSlotStart = ((IAddsTinkersSlots) this.inventorySlots).getTinkersSlotStart();
        }

        if (ModCompat.isBaublesPresent() && this.inventorySlots instanceof IAddsBaubleSlots) {
            baublesSlotStart = ((IAddsBaubleSlots) this.inventorySlots).getBaublesSlotStart();
        }

        if (ModCompat.isGalacticraftPresent() && this.inventorySlots instanceof IAddsGCSlots) {
            gcSlotStart = ((IAddsGCSlots) this.inventorySlots).getGCSlotStart();
        }

        if (ModCompat.isTravellersGearPresent() && this.inventorySlots instanceof IAddsTGSlots) {
            tgSlotStart = ((IAddsTGSlots) this.inventorySlots).getTGSlotStart();
        }

        int accessorySlotStart = dws$GetMinValueFromList(tinkersSlotStart, baublesSlotStart, gcSlotStart, tgSlotStart);

        for (int i = accessorySlotStart; i < this.inventorySlots.inventorySlots.size(); i++) {
            dws$drawEmptySlot(this.inventorySlots.getSlot(i));
        }
    }

    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 86),
                    require = 1)
    private int modifyDrawStringXOffset(int constant) {
        return constant + 162;
    }

    @Unique
    private void dws$drawEmptySlot(Slot slot) {
        int x = this.guiLeft + slot.xDisplayPosition;
        int y = this.guiTop + slot.yDisplayPosition;

        //draw empty slot
        this.drawTexturedModalRect(x - 1, y - 1, 144,198, 18,18);
    }

    @Unique
    private int dws$GetMinValueFromList(int value, int ... values) {
        int min = value;

        for (int i : values) {
            min = Math.min(min, i);
        }

        return min;
    }
}
