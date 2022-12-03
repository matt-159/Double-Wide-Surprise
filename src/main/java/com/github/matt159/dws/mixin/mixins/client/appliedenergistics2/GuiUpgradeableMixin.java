package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiUpgradeable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiUpgradeable.class)
public abstract class GuiUpgradeableMixin {
    @ModifyConstant(method = {  "<init>(Lappeng/container/implementations/ContainerUpgradeable;)V",
                                "drawBG"    },
                    constant =  {   @Constant(intValue = 177),
                                    @Constant(intValue = 178),
                                    @Constant(intValue = 211),
                                    @Constant(intValue = 246)   },
                    remap = false,
                    require = 7)
    private int modifyGuiXSize(int constant) {
        return constant + 162;
    }
}
