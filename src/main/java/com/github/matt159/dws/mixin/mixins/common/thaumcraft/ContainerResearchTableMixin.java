package com.github.matt159.dws.mixin.mixins.common.thaumcraft;

import com.github.matt159.dws.interfaces.IDWSContainer;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.common.container.ContainerResearchTable;

import net.minecraft.inventory.Container;

@Mixin(ContainerResearchTable.class)
public abstract class ContainerResearchTableMixin extends Container implements IDWSContainer {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 70),
                    require = 1)
    private int modifyResearchNoteSlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "bindPlayerInventory",
                    constant = @Constant(intValue = 9),
                    remap = false,
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }

    @ModifyConstant(method = "bindPlayerInventory",
                    constant = @Constant(intValue = 48),
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return 8;
    }
}
