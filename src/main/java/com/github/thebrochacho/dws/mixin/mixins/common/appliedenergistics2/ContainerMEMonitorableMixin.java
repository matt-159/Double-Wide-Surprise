package com.github.thebrochacho.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerMEMonitorable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerMEMonitorable.class)
public abstract class ContainerMEMonitorableMixin {
    @ModifyConstant(method = "<init>(Lnet/minecraft/entity/player/InventoryPlayer;Lappeng/api/storage/ITerminalHost;Z)V",
                    constant = @Constant(intValue = 206),
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 143;
    }
}
