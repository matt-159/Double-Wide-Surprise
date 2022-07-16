package com.github.matt159.dws.mixins.common.gregtech;

import gregtech.api.gui.GT_ContainerMetaTile_Machine;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.common.gui.GT_Container_Boiler;
import net.minecraft.entity.player.InventoryPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container_Boiler.class)
public abstract class GT_Container_BoilerMixin extends GT_ContainerMetaTile_Machine {

    public GT_Container_BoilerMixin(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
    }

    @ModifyConstant(method = "addSlots",
                    constant = {
                        @Constant(intValue = 44),
                        @Constant(intValue = 116)
                    },
                    remap = false,
                    require = 1)
    private int modifySlotXOffsets(int constant) {
        return constant + 81;
    }
}
