package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import com.github.matt159.dws.interfaces.dws.IAddsBaubleSlots;
import com.github.matt159.dws.interfaces.dws.IAddsGCSlots;
import com.github.matt159.dws.interfaces.dws.IAddsTGSlots;
import com.github.matt159.dws.interfaces.dws.IAddsTinkersSlots;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerPlayer.class)
public abstract class ContainerPlayerMixin extends Container implements IAddsBaubleSlots,
                                                                        IAddsTinkersSlots,
                                                                        IAddsGCSlots,
                                                                        IAddsTGSlots {

    @ModifyConstant(method = "<init>",
                    constant = {    @Constant(intValue = 88),
                                    @Constant(intValue = 144)   },
                    require = 1)
    private int modifyCraftingSlotsXOffset(int constant) {
        return constant + 162;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private int modifyColumnCount(int constant) {
        return 18;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 36),
                    require = 4)
    private int modifyHotbarSlotStart(int constant) {
        return 63;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 45),
                    require = 6)
    private int modifyHotbarSlotEnd(int constant) {
        return 81;
    }
}
