package com.github.matt159.dws.mixin.mixins.common.inventorytweaks;

import com.github.matt159.dws.util.DWSUtil;
import invtweaks.InvTweaksContainerManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;

import java.util.List;

@Mixin(InvTweaksContainerManager.class)
public abstract class InvTweaksContainerManagerMixin {
    @Shadow(remap = false)
    private Container container;

    @Redirect(method = "initSlots",
              at = @At(value = "INVOKE",
                       target = "Ljava/util/List;size()I",
                       ordinal = 0),
              require = 1,
              remap = false)
    private int redirectEndSlotRange(List instance) {
        if (this.container instanceof ContainerPlayer) {
            return instance.size() - DWSUtil.getAccessorySlotCount();
        }

        return instance.size();
    }

    @ModifyConstant(method = "*",
                    constant = { @Constant(intValue = 9),
                                 @Constant(intValue = 36) },
                    remap = false,
                    require = 5)
    private int modifyInventorySize(int constant) {
        return constant * 2;
    }

//    @Redirect(method = "initSlots",
//              at = @At(value = "INVOKE",
//                       target = "Ljava/util/List;size()I"),
//              remap = false,
//              require = 1)
//    private int redirectSize(List<Slot> instance) {
//        return instance.size() - (this.container instanceof ContainerPlayer ? DWSUtil.getAccessorySlotCount() : 0);
//    }
}
