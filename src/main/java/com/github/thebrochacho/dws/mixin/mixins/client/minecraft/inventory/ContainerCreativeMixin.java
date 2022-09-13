package com.github.thebrochacho.dws.mixin.mixins.client.minecraft.inventory;

import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiContainerCreative.ContainerCreative.class)
public abstract class ContainerCreativeMixin extends Container {
    @ModifyConstant(method = "<init>",
                    constant = {    @Constant(  intValue = 9,
                                                ordinal = 0),
                                    @Constant(  intValue = 9,
                                                ordinal = 1),
                                    @Constant(  intValue = 9,
                                                ordinal = 3)    },
                    require = 1)
    private int modifyCreativeInventorySize(int constant) {
        return 18;
    }

    @ModifyConstant(method = "scrollTo",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private int modifyRange(int constant) {
        return 18;
    }

    @ModifyConstant(method = "func_148328_e",
                    constant = @Constant(intValue = 45),
                    require = 1)
    private int modifyDisplayScrollBarRequirement(int constant) {
        return 90;
    }
}
