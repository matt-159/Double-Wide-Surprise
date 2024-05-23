package com.github.matt159.dws.mixin.mixins.common.gregtech;

import com.github.matt159.dws.util.Constants;
import gregtech.api.interfaces.ITexture;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_MetaTileEntity_BasicMachine.class)
public abstract class GT_MetaTileEntity_BasicMachineMixin extends GT_MetaTileEntity_BasicTank {


    public GT_MetaTileEntity_BasicMachineMixin(int aID,
                                               String aName,
                                               String aNameRegional,
                                               int aTier,
                                               int aInvSlotCount,
                                               String aDescription,
                                               ITexture... aTextures) {
        super(aID, aName, aNameRegional, aTier, aInvSlotCount, aDescription, aTextures);
    }

    public GT_MetaTileEntity_BasicMachineMixin(int aID,
                                               String aName,
                                               String aNameRegional,
                                               int aTier,
                                               int aInvSlotCount,
                                               String[] aDescription,
                                               ITexture... aTextures) {
        super(aID, aName, aNameRegional, aTier, aInvSlotCount, aDescription, aTextures);
    }

    public GT_MetaTileEntity_BasicMachineMixin(String aName,
                                               int aTier,
                                               int aInvSlotCount,
                                               String aDescription,
                                               ITexture[][][] aTextures) {
        super(aName, aTier, aInvSlotCount, aDescription, aTextures);
    }

    public GT_MetaTileEntity_BasicMachineMixin(String aName,
                                               int aTier,
                                               int aInvSlotCount,
                                               String[] aDescription,
                                               ITexture[][][] aTextures) {
        super(aName, aTier, aInvSlotCount, aDescription, aTextures);
    }

    @ModifyConstant(method = "getCircuitSlotX",
                    constant = @Constant(intValue = 153),
                    remap = false,
                    require = 1)
    private int modifyCircuitSlotXOffset(int constant) {
        return constant + Constants.GENERAL_DWS_GUI_WIDTH;
    }
}
