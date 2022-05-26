package com.github.thebrochacho.dws.mixins.common.tinker;

import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.entity.player.InventoryPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tconstruct.smeltery.inventory.ActiveContainer;
import tconstruct.smeltery.inventory.ActiveSlot;
import tconstruct.smeltery.inventory.SmelteryContainer;
import tconstruct.smeltery.logic.SmelteryLogic;

@Mixin(SmelteryContainer.class)
public abstract class SmelteryContainerMixin extends ActiveContainer {

    @Shadow(remap = false)
    @Final
    public int smelterySize;

    @Shadow(remap = false)
    private int slotRow;

    @Shadow(remap = false)
    public int columns;

    @Inject(method = "<init>",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private void addSlots(InventoryPlayer inventoryplayer, SmelteryLogic smeltery, CallbackInfo ci) {
        this.activeInventorySlots.clear();
        this.inventorySlots.clear();

        this.columns = 10;

        for (int i = 0; i < this.smelterySize; i++) {
            int x = i % 10;
            int y = i / 10;

            this.addDualSlotToContainer(new ActiveSlot(smeltery, i, (x * 22) + 12, (y * 18) + 16, y < 3));
        }

        DWSUtil.addDWSSlotsToContainer(this, inventoryplayer);
    }

    @ModifyVariable(method = "scrollTo",
                    at = @At("STORE"),
                    name = "slots",
                    remap = false,
                    require = 1)
    private int modifySlots(int slots) {
        return 3 * columns;
    }

    @Redirect(  method = "scrollTo",
                at = @At(   value = "INVOKE",
                            target = "Ltconstruct/smeltery/inventory/SmelteryContainer;updateRows(I)I"),
                remap = false,
                require = 1)
    private int redirectUpdateRows(SmelteryContainer instance, int rowPos) {
        return DWSUpdateRows(rowPos);
    }

    private int DWSUpdateRows(int invRow) {
        if (invRow != this.slotRow)
        {
            this.slotRow = invRow;
            int basePos = invRow * this.columns;
            for (int iter = 0; iter < activeInventorySlots.size(); iter++)
            {
                ActiveSlot slot = (ActiveSlot) activeInventorySlots.get(iter);
                boolean a = slot.activeSlotNumber >= basePos;
                boolean b = slot.activeSlotNumber < basePos + this.columns * 3;
                slot.setActive(a && b);

                int xPos = (iter - basePos) % this.columns;
                int yPos = (iter - basePos) / this.columns;
                slot.xDisplayPosition = 12 + 22 * xPos;
                slot.yDisplayPosition = 16 + 18 * yPos;
            }
            return this.slotRow;
        }
        return -1;
    }
}
