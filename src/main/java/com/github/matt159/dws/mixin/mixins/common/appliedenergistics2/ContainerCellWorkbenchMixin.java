package com.github.matt159.dws.mixin.mixins.common.appliedenergistics2;

import appeng.api.implementations.IUpgradeableHost;
import appeng.container.implementations.ContainerCellWorkbench;
import appeng.container.implementations.ContainerUpgradeable;
import com.github.matt159.dws.util.Constants;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ContainerCellWorkbench.class)
public abstract class ContainerCellWorkbenchMixin extends ContainerUpgradeable {
    public ContainerCellWorkbenchMixin(InventoryPlayer ip, IUpgradeableHost te) {
        super(ip, te);
    }

    @ModifyConstant(method = "setupConfig",
                    constant = {    @Constant(intValue = 152),
                                    @Constant(intValue = 187)   },
                    remap = false,
                    require = 2)
    private int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @Redirect(method = "setupConfig",
              at = @At(value = "INVOKE",
                       target = "Lappeng/container/implementations/ContainerCellWorkbench;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
                       ordinal = 1),
              require = 1)
    private Slot redirectFakeSlotConstructor(ContainerCellWorkbench instance, Slot slot) {
        slot.xDisplayPosition += Constants.GENERAL_X_OFFSET;
        return this.addSlotToContainer(slot);
    }
}
