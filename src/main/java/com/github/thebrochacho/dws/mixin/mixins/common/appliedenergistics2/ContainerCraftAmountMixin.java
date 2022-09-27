package com.github.thebrochacho.dws.mixin.mixins.common.appliedenergistics2;

import appeng.container.implementations.ContainerCraftAmount;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ContainerCraftAmount.class)
public abstract class ContainerCraftAmountMixin {
    @Shadow(remap = false)
    @Final @Mutable
    private Slot craftingItem;

    @Redirect(method = "<init>",
              at = @At(value = "FIELD",
                       target = "Lappeng/container/implementations/ContainerCraftAmount;craftingItem:Lnet/minecraft/inventory/Slot;",
                       opcode = Opcodes.PUTFIELD),
              remap = false,
              require = 1)
    private void redirectNewXYPosition(ContainerCraftAmount instance, Slot value) {
        value.xDisplayPosition += 78;
        value.yDisplayPosition -= 8;

        this.craftingItem = value;
    }
}
