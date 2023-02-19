package com.github.matt159.dws.mixin.mixins.common.chisel;

import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import team.chisel.inventory.ContainerAutoChisel;

@Mixin(ContainerAutoChisel.class)
public abstract class ContainerAutoChiselMixin {
    @Shadow(remap = false)
    protected abstract void addSlot(IInventory inv, int id, int x, int y);

    @Redirect(method = "<init>",
              at = @At(value = "INVOKE",
                       target = "Lteam/chisel/inventory/ContainerAutoChisel;addSlot(Lnet/minecraft/inventory/IInventory;III)V"),
              require = 8)
    private void redirectAddSlot(ContainerAutoChisel instance, IInventory inv, int id, int x, int y) {
        this.addSlot(inv, id, x + 81, y);
    }

    @ModifyConstant(method = "bindPlayerInventory",
                    constant = @Constant(intValue = 9),
                    remap = false,
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
