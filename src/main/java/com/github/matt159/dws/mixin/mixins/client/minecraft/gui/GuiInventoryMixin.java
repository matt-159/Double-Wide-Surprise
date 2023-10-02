package com.github.matt159.dws.mixin.mixins.client.minecraft.gui;

import com.github.matt159.dws.DoubleWideSurprise;
import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.util.SlotLayoutManager;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

import lombok.val;
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
        int accessorySlotStart = SlotLayoutManager.getFirstAccessorySlot();

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

    @Override
    public void handleInput() {
        super.handleInput();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int clickedButton) {
        val rightClick = 1;

        if (clickedButton == rightClick && isCtrlKeyDown()) {
            val slot = this.dws$findSlotForPosition(mouseX, mouseY);

            if (slot != null) {
                val itemStack = slot.getStack();

                if (itemStack != null) {
                    DoubleWideSurprise.info(itemStack.getDisplayName());

                    return;
                }
            }
        }

        super.mouseClicked(mouseX, mouseY, clickedButton);
    }


    @Unique
    private Slot dws$findSlotForPosition(int x, int y) {
        for(int k = 0; k < this.inventorySlots.inventorySlots.size(); ++k) {
            Slot slot = (Slot)this.inventorySlots.inventorySlots.get(k);
            if (this.func_146978_c(slot.xDisplayPosition, slot.yDisplayPosition, 16, 16, x, y)) {
                return slot;
            }
        }

        return null;
    }
}
