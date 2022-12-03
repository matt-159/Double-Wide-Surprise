package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import codechicken.nei.ContainerCreativeInv;
import com.github.matt159.dws.interfaces.minecraft.IEntityPlayerMixin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Container.class)
public abstract class ContainerMixin {

    @ModifyConstant(method = "slotClick",
                    constant = @Constant(intValue = 9))
    private int modifyHotbarRangeCheck(int constant) {
        return 18;
    }

    @Inject(method = "onContainerClosed",
            at = @At("RETURN"),
            require = 1)
    private void injectPlayerInventoryReorganization(EntityPlayer player, CallbackInfo ci) {
        Container c = ((Container) (Object) this);
        if (c instanceof ContainerPlayer || c instanceof ContainerCreativeInv) {
            return;
        }

        if (((IEntityPlayerMixin) player).isInventoryReorganized()) {
            ((IEntityPlayerMixin) player).resetInventory();
        }
    }
}
