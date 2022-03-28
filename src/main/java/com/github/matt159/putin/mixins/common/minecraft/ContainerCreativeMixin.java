package com.github.matt159.putin.mixins.common.minecraft;

import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiContainerCreative.ContainerCreative.class)
public abstract class ContainerCreativeMixin extends Container {

    @Shadow public abstract void scrollTo(float p_148329_1_);

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    private void addSlotsToContainer(EntityPlayer player, CallbackInfo ci) {
        ((GuiContainerCreative.ContainerCreative) (Object) (this)).inventorySlots.clear();
        InventoryPlayer inventoryPlayer = player.inventory;

        int i;
        for(i = 0; i < 5; ++i) {
            for(int j = 0; j < 18; ++j) {
                this.addSlotToContainer(new Slot(GuiContainerCreative.field_147060_v, i * 18 + j, 9 + j * 18, 18 + i * 18));
            }
        }

        //left half of hotbar
        int col;
        for (col = 0; col < 9; ++col) {
            this.addSlotToContainer(new Slot(inventoryPlayer, col, 9 + col * 18, 112));
        }

        //right half of hotbar
        for (col = 0; col < 9; ++col) {
            this.addSlotToContainer(new Slot(inventoryPlayer, col + 63, 9 + (col + 9) * 18, 112));
        }

        this.scrollTo(0.0F);
    }

    @ModifyConstant(method = "scrollTo",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private int modifyRange(int constant) {
        return 18;
    }

    @ModifyConstant(method = "func_148328_e",
                    constant = @Constant(intValue = 45),
                    require = 1)
    private int modifyDisplayScrollBarRequirement(int constant) {
        return 90;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
