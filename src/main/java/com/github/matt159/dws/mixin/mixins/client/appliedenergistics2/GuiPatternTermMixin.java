package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiPatternTerm;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GuiPatternTerm.class)
public abstract class GuiPatternTermMixin {
    @ModifyArg(method = "initGui",
               at = @At(value = "INVOKE",
                        target = "Lappeng/client/gui/widgets/GuiImgButton;<init>(IILjava/lang/Enum;Ljava/lang/Enum;)V"),
               index = 0,
               require = 1)
    private int modifyGuiImgButtonXOffsets(int arg) {
        return arg + Constants.GENERAL_X_OFFSET;
    }

    @ModifyArg(method = "initGui",
               at = @At(value = "INVOKE",
                        target = "Lappeng/client/gui/widgets/GuiTabButton;<init>(IILnet/minecraft/item/ItemStack;Ljava/lang/String;Lnet/minecraft/client/renderer/entity/RenderItem;)V"),
               index = 0,
               require = 1)
    private int modifyGuiTabButtonXOffset(int arg) {
        return arg + Constants.GENERAL_X_OFFSET;
    }
}
