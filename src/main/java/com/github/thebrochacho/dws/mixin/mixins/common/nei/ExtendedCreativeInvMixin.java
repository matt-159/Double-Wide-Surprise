package com.github.thebrochacho.dws.mixin.mixins.common.nei;

import codechicken.nei.ExtendedCreativeInv;
import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ExtendedCreativeInv.class)
public abstract class ExtendedCreativeInvMixin implements IInventory {

    @Override
    public int getSizeInventory() {
        return 108;
    }
}
