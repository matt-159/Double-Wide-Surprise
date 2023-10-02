package com.github.matt159.dws.mixin.mixins.common.railcraft;

import com.github.matt159.dws.interfaces.IDWSContainer;
import com.github.matt159.dws.util.Constants;
import mods.railcraft.common.gui.containers.ContainerSteamOven;
import mods.railcraft.common.gui.containers.RailcraftContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(ContainerSteamOven.class)
public abstract class ContainerSteamOvenMixin extends RailcraftContainer implements IDWSContainer {
    @ModifyConstant(method = "<init>",
                    constant = {
                        @Constant(intValue = 94),
                        @Constant(intValue = 8, ordinal = 0),
                        @Constant(intValue = 116)
                    },
                    require = 3)
    private int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH;
    }

    @ModifyConstant(method = "<init>",
                    slice = @Slice(from = @At(value = "INVOKE",
                                              target = "Lnet/minecraft/inventory/SlotFurnace;<init>(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/inventory/IInventory;III)V"),
                                   to = @At("TAIL")),
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
