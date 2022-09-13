package com.github.thebrochacho.dws.mixin.mixins.common.minecraft.inventory;

import com.github.thebrochacho.dws.inventory.slots.minecraft.SlotHorseArmor;
import com.github.thebrochacho.dws.inventory.slots.minecraft.SlotSaddle;
import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerHorseInventory.class)
public abstract class ContainerHorseInventoryMixin extends Container {

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void addSlotsToContainer(IInventory inventoryPlayer, IInventory inventoryHorse, EntityHorse horse, CallbackInfo ci) {
        this.inventorySlots.clear();

        this.addSlotToContainer(new SlotSaddle(inventoryHorse, 0, 89, 18));
        this.addSlotToContainer(new SlotHorseArmor(horse, inventoryHorse, 1, 89, 36));

        int var4 = 3;
        int row, col;

        if (horse.isChested()) {
            for(row = 0; row < var4; ++row) {
                for(col = 0; col < 5; ++col) {
                    this.addSlotToContainer(new Slot(inventoryHorse, 2 + col + row * 5, 161 + col * 18, 18 + row * 18));
                }
            }
        }

        DWSUtil.addDWSSlotsToContainer(this, inventoryPlayer);
    }
}
