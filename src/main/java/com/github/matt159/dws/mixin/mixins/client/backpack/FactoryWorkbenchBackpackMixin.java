package com.github.matt159.dws.mixin.mixins.client.backpack;

import com.github.matt159.dws.util.Constants;
import de.eydamos.backpack.factory.FactoryWorkbenchBackpack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FactoryWorkbenchBackpack.class)
public abstract class FactoryWorkbenchBackpackMixin {
    @ModifyConstant(method = "getGuiContainer(Lde/eydamos/backpack/saves/BackpackSave;[Lnet/minecraft/inventory/IInventory;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/client/gui/inventory/GuiContainer;",
                    constant = { @Constant(intValue = 66),
                                 @Constant(intValue = 81),
                                 @Constant(intValue = 88),
                                 @Constant(intValue = 90) },
                    remap = false,
                    require = 4)
    private int modifyButtonXPositions(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
