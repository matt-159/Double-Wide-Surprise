package com.github.matt159.dws.mixin.mixins.client.minecraft.gui;

import com.github.matt159.dws.gui.SlotOverlays;
import com.github.matt159.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiInventory.class)
public abstract class GuiInventoryMixin extends InventoryEffectRenderer implements IDWSGui {
    public GuiInventoryMixin(Container container) {
        super(container);
    }

//    @Inject(method = "drawGuiContainerBackgroundLayer",
//            at = @At(   value = "INVOKE",
//                        target = "Lnet/minecraft/client/gui/inventory/GuiInventory;drawTexturedModalRect(IIIIII)V",
//                        shift = At.Shift.AFTER),
//            require = 1)
//    private void injectDrawExtraSlots(float f, int i, int j, CallbackInfo ci) {
//        if (Config.isBaublesLoaded) {
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.BAUBLES_SLOT_START + 0), Hints.AMULET);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.BAUBLES_SLOT_START + 1), Hints.RING);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.BAUBLES_SLOT_START + 2), Hints.RING);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.BAUBLES_SLOT_START + 3), Hints.BAUBLE_BELT);
//        }
//
//        if (Config.isTinkersLoaded) {
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.TINKERS_SLOT_START + 0), Hints.MASK);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.TINKERS_SLOT_START + 1), Hints.GLOVE);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.TINKERS_SLOT_START + 2), Hints.TINKERS_BELT);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.TINKERS_SLOT_START + 3), Hints.KNAPSACK);
//
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.TINKERS_SLOT_START + 4), Hints.RED_CANISTER);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.TINKERS_SLOT_START + 5), Hints.YELLOW_CANISTER);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.TINKERS_SLOT_START + 6), Hints.GREEN_CANISTER);
//        }
//
//        if (Config.isTravellersGearLoaded) {
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.TG_SLOT_START + 0), Hints.CLOAK);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.TG_SLOT_START + 1), Hints.PAULDRON);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.TG_SLOT_START + 2), Hints.VAMBRACE);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.TG_SLOT_START + 3), Hints.TITLE);
//        }
//
//        if (Config.isGalacticraftLoaded) {
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.GC_SLOT_START + 0), Hints.THERMAL_HELMET);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.GC_SLOT_START + 1), Hints.THERMAL_CHEST);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.GC_SLOT_START + 2), Hints.THERMAL_PANTS);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.GC_SLOT_START + 3), Hints.THERMAL_BOOTS);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.GC_SLOT_START + 4), Hints.PARACHUTE);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.GC_SLOT_START + 5), Hints.OXYGEN_MASK);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.GC_SLOT_START + 6), Hints.OXYGEN_TANK);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.GC_SLOT_START + 7), Hints.FREQUENCY_MODULE);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.GC_SLOT_START + 8), Hints.OXYGEN_GEAR);
//            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerDWS.GC_SLOT_START + 9), Hints.OXYGEN_TANK);
//        }
//
//        ContainerDWS.nullSlots.forEach(nullSlotXY -> {
//            this.drawTexturedModalRect( guiLeft + nullSlotXY.getLeft() - 1,
//                                        guiTop + nullSlotXY.getRight() - 1,
//                                        96,
//                                        208,
//                                        18,
//                                        18);
//            });
//    }

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
