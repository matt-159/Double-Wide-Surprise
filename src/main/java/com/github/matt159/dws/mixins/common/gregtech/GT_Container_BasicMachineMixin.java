package com.github.matt159.dws.mixins.common.gregtech;

import gregtech.api.gui.GT_Container_BasicMachine;
import gregtech.api.gui.GT_Container_BasicTank;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container_BasicMachine.class)
public abstract class GT_Container_BasicMachineMixin extends GT_Container_BasicTank {

    public GT_Container_BasicMachineMixin(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
    }

    @ModifyConstant(method = "addSlots",
                    constant = {
                            @Constant(intValue = 8),
                            @Constant(intValue = 17),
                            @Constant(intValue = 26),
                            @Constant(intValue = 35),
                            @Constant(intValue = 53),
                            @Constant(intValue = 80),
                            @Constant(intValue = 107),
                            @Constant(intValue = 125),
                            @Constant(intValue = 143)
                    },
                    remap = false,
                    require = 1)
    private int ModifyXOffsets(int constant) {
        return constant + 81;
    }
}
