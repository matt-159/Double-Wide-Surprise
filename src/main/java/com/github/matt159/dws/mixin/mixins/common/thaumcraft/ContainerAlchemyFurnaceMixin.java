package com.github.matt159.dws.mixin.mixins.common.thaumcraft;

import com.github.matt159.dws.interfaces.IDWSContainer;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.common.container.ContainerAlchemyFurnace;

import net.minecraft.inventory.Container;

@Mixin(ContainerAlchemyFurnace.class)
public abstract class ContainerAlchemyFurnaceMixin extends Container implements IDWSContainer {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 80),
                    require = 2)
    private int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
