package com.github.matt159.dws.mixin.mixins.common.storagedrawers;

import com.jaquadro.minecraft.storagedrawers.inventory.ContainerFramingTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerFramingTable.class)
public abstract class ContainerFramingTableMixin {

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerContainerSize(int constant) {
        return 18;
    }

    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 23),
                                 @Constant(intValue = 50),
                                 @Constant(intValue = 102),
                                 @Constant(intValue = 133) },
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
