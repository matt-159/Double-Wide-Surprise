package com.github.thebrochacho.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiPatternTerm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GuiPatternTerm.class)
public abstract class GuiPatternTermMixin {
    @ModifyArg(method = "initGui",
               at = @At(value = "INVOKE",
                        target = "Lappeng/client/gui/widgets/GuiImgButton;<init>(IILjava/lang/Enum;Ljava/lang/Enum;)V"),
               index = 0,
               remap = false,
               require = 1)
    private int modifyGuiImgButtonXOffsets(int arg) {
        return arg + 81;
    }

    @ModifyArg(method = "initGui",
               at = @At(value = "INVOKE",
                        target = "Lappeng/client/gui/widgets/GuiTabButton;<init>(IILnet/minecraft/item/ItemStack;Ljava/lang/String;Lnet/minecraft/client/renderer/entity/RenderItem;)V"),
               index = 0,
               remap = false,
               require = 1)
    private int modifyGuiTabButtonXOffset(int arg) {
        return arg + 81;
    }
}
