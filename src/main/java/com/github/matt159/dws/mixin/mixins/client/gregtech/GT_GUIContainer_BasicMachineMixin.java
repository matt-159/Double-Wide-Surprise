package com.github.matt159.dws.mixin.mixins.client.gregtech;

import com.github.matt159.dws.util.Constants;
import gregtech.api.gui.GT_ContainerMetaTile_Machine;
import gregtech.api.gui.GT_GUIContainerMetaTile_Machine;
import gregtech.api.gui.GT_GUIContainer_BasicMachine;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.entity.player.InventoryPlayer;

@Mixin(GT_GUIContainer_BasicMachine.class)
public abstract class GT_GUIContainer_BasicMachineMixin extends GT_GUIContainerMetaTile_Machine {
    public GT_GUIContainer_BasicMachineMixin(GT_ContainerMetaTile_Machine aContainer, String aGUIbackground) {
        super(aContainer, aGUIbackground);
    }

    public GT_GUIContainer_BasicMachineMixin(InventoryPlayer aInventoryPlayer,
                                             IGregTechTileEntity aTileEntity,
                                             String aGUIbackground) {
        super(aInventoryPlayer, aTileEntity, aGUIbackground);
    }

    @ModifyConstant(method = "setupTooltips",
                    constant = {
                        @Constant(intValue = 79),
                    },
                    remap = false,
                    require = 1)
    private int modifyXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = {
                        @Constant(intValue = 7),
                        @Constant(intValue = 25),
                        @Constant(intValue = 78),
                        @Constant(intValue = 79)
                    },
                    require = 1)
    private int modifyProgressBarXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifyUVXOffset(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH;
    }
}
