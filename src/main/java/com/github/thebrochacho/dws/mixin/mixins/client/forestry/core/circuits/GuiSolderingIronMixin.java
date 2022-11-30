package com.github.thebrochacho.dws.mixin.mixins.client.forestry.core.circuits;

import forestry.core.circuits.GuiSolderingIron;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiSolderingIron.class)
public abstract class GuiSolderingIronMixin {

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = { @Constant(intValue = 8,
                                           ordinal = 0),
                                 @Constant(intValue = 17),
                                 @Constant(intValue = 32) },
                    require = 2)
    private int modifyTextXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "initGui",
                    constant = { @Constant(intValue = 12,
                                           ordinal = 0),
                                 @Constant(intValue = 130) },
                    require = 2)
    private int modifyButtonXOffset(int constant) {
        return constant + 81;
    }
}
