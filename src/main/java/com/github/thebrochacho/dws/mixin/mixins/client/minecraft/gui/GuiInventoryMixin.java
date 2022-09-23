package com.github.thebrochacho.dws.mixin.mixins.client.minecraft.gui;

import com.github.thebrochacho.dws.interfaces.IDWSGui;
import com.github.thebrochacho.dws.interfaces.dws.*;
import com.github.thebrochacho.dws.inventory.slots.SlotDWS;
import com.github.thebrochacho.dws.util.ModCompat;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
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

        int accessorySlotStart = getMinValueFromList(tinkersSlotStart, baublesSlotStart, gcSlotStart, tgSlotStart);

        for (int i = accessorySlotStart; i < this.inventorySlots.inventorySlots.size(); i++) {
            SlotDWS slot = (SlotDWS) this.inventorySlots.getSlot(i);

            drawSlotAndOverlay(slot);
        }
    }

    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 86),
                    require = 1)
    private int modifyDrawStringXOffset(int constant) {
        return constant + 162;
    }

    private void drawSlotAndOverlay(SlotDWS slot) {
        int x = this.guiLeft + slot.xDisplayPosition;
        int y = this.guiTop + slot.yDisplayPosition;

        //draw empty slot
//        GL11.glColor3f(1.0F, 1.0F, 1.0F);
//        GL11.glEnable(3042);
        this.drawTexturedModalRect(x - 1, y - 1, 144,198, 18,18);

        if(!slot.getHasStack()) {
            int u = 162 + 18 * slot.type.getX() + 1;
            int v = 180 + 18 * slot.type.getY() + 1;

            this.drawTexturedModalRect( x, y, u, v, 16, 16);
        }
    }

    private int getMinValueFromList(int value, int ... values) {
        int min = value;

        for (int i : values) {
            min = Math.min(min, i);
        }

        return min;
    }
}
