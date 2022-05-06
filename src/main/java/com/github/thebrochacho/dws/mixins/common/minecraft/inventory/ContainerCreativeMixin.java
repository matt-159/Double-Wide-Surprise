package com.github.thebrochacho.dws.mixins.common.minecraft.inventory;

import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiContainerCreative.ContainerCreative.class)
public class ContainerCreativeMixin {

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    private void addSlotsToContainer(EntityPlayer player, CallbackInfo ci) {
        GuiContainerCreative.ContainerCreative cc = (GuiContainerCreative.ContainerCreative) (Object) (this);
        cc.inventorySlots.clear();
        InventoryPlayer inventoryPlayer = player.inventory;

        int i;
        for(i = 0; i < 5; ++i) {
            for(int j = 0; j < 18; ++j) {
                DWSUtil.addSlotToContainer(cc, new Slot(GuiContainerCreative.field_147060_v, i * 18 + j, 9 + j * 18, 18 + i * 18));
            }
        }

        //left half of hotbar
        int col;
        for (col = 0; col < 9; ++col) {
            DWSUtil.addSlotToContainer(cc, new Slot(inventoryPlayer, col, 9 + col * 18, 112));
        }

        //right half of hotbar
        for (col = 0; col < 9; ++col) {
            DWSUtil.addSlotToContainer(cc, new Slot(inventoryPlayer, col + 63, 9 + (col + 9) * 18, 112));
        }

        cc.scrollTo(0.0F);
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
}
