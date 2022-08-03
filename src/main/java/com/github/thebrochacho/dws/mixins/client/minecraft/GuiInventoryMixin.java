package com.github.thebrochacho.dws.mixins.client.minecraft;

import com.github.thebrochacho.dws.Config;
import com.github.thebrochacho.dws.gui.SlotOverlays;
import com.github.thebrochacho.dws.interfaces.IDWSGui;
import com.github.thebrochacho.dws.inventory.ContainerDWS;
import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import org.lwjgl.opengl.GL11;
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
    private void injectDrawExtraSlots(float f, int i, int j, CallbackInfo ci) {
        if (Config.isBaublesLoaded) {
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.BAUBLES_SLOT_START + 0), SlotOverlays.Hints.AMULET);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.BAUBLES_SLOT_START + 1), SlotOverlays.Hints.RING);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.BAUBLES_SLOT_START + 2), SlotOverlays.Hints.RING);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.BAUBLES_SLOT_START + 3), SlotOverlays.Hints.BAUBLE_BELT);
        }
    }

    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 86),
                    require = 1)
    private int modifyDrawStringXOffset(int constant) {
        return constant + 162;
    }

    private void drawSlotAndOverlay(Slot slot, SlotOverlays.Hints hint) {
        int x = this.guiLeft + slot.xDisplayPosition - 1;
        int y = this.guiTop + slot.yDisplayPosition - 1;

        //draw empty slot
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        GL11.glEnable(3042);
        this.drawTexturedModalRect(x, y, 96,176, 18,18);

        if(!slot.getHasStack())
        {
            this.drawTexturedModalRect(x + 1, y + 1, hint.getX(), hint.getY(), 16,16);
        }
    }
}
