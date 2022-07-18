package com.github.thebrochacho.dws.mixins.common.minecraft.inventory;

import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerWorkbench.class)
public abstract class ContainerWorkbenchMixin extends Container {

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void addSlotsToContainer(InventoryPlayer inventoryPlayer, World world, int x, int y, int z, CallbackInfo ci) {
        this.inventorySlots.clear();
        InventoryCrafting craftMatrix = ((ContainerWorkbench) (Object) (this)).craftMatrix;

        this.addSlotToContainer(new SlotCrafting(inventoryPlayer.player, craftMatrix, ((ContainerWorkbench) (Object) (this)).craftResult, 0, 205, 35));

        int row;
        int col;
        for(row = 0; row < 3; ++row) {
            for(col = 0; col < 3; ++col) {
                this.addSlotToContainer(new Slot(craftMatrix, col + row * 3, 111 + col * 18, 17 + row * 18));
            }
        }

        DWSUtil.addDWSSlotsToContainer(this, inventoryPlayer);
    }
}
