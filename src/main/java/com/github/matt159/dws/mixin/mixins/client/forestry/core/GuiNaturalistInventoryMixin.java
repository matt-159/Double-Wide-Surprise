package com.github.matt159.dws.mixin.mixins.client.forestry.core;

import forestry.core.gui.GuiNaturalistInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;
import sun.security.pkcs11.wrapper.CK_INFO;

@Mixin(GuiNaturalistInventory.class)
public abstract class GuiNaturalistInventoryMixin {
    @ModifyConstant(method = { "initGui",
                               "drawGuiContainerBackgroundLayer" },
                    constant = { @Constant(intValue = 10,
                                           slice = "tens"),
                                 @Constant(intValue = 95,
                                           slice = ""),
                                 @Constant(intValue = 99),
                                 @Constant(intValue = 180) },
                    slice = { @Slice(id = "tens",
                                     from = @At(value = "INVOKE",
                                     target = "Lforestry/core/gui/GuiNaturalistInventory;getIndividualAtPosition(II)Lforestry/api/genetics/IIndividual;",
                                     remap = false),
                                     to = @At("TAIL")) },
                    require = 1)
    private int modifyButtonXOffset(int constant) {
        return constant + 71;
    }

    @ModifyConstant(method = "displaySpeciesInformation",
                    constant = { @Constant(intValue = 10),
                                 @Constant(intValue = 75) },
                    remap = false,
                    require = 1)
    private int modifyXOffsets(int constant) {
        return constant + 71;
    }

    @ModifyConstant(method = { "drawMutationIcon",
                               "drawUnknownIcon" },
                    constant = { @Constant(intValue = 196),
                                 @Constant(intValue = 212),
                                 @Constant(intValue = 228) },
                    remap = false,
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return constant + 142;
    }
}
