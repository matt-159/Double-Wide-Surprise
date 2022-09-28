package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiInscriber;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiInscriber.class)
public abstract class GuiInscriberMixin {
    @ModifyConstant(method = { "<init>",
                               "drawBG" },
                    constant = { @Constant(intValue = 177),
                                 @Constant(intValue = 178),
                                 @Constant(intValue = 211),
                                 @Constant(intValue = 246) },
                    remap = false,
                    require = 7)
    private int modifyGuiXSize(int constant) {
        return constant + 162;
    }

    @ModifyConstant(method = { "initGui",
                               "drawBG" },
                    constant = @Constant(intValue = 135),
                    remap = false,
                    require = 3)
    private int modifyProgressBarUVXOffset(int constant) {
        return constant + 81;
    }

//    @ModifyConstant(method = "drawBG",
//            constant =  {   @Constant(intValue = 177),
//                    @Constant(intValue = 178)   },
//            remap = false,
//            require = 23)
//    private int modifyGuiXSize(int constant) {
//        return constant + 162;
//    }
}
