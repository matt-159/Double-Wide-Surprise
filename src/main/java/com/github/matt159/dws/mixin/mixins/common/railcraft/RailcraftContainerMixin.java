package com.github.matt159.dws.mixin.mixins.common.railcraft;

import com.github.matt159.dws.interfaces.IDWSContainer;
import mods.railcraft.common.gui.containers.RailcraftContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.inventory.Container;

@Mixin(RailcraftContainer.class)
public abstract class RailcraftContainerMixin extends Container {
    @ModifyConstant(method = "*",
                    constant = {
                        @Constant(intValue = 9),
                        @Constant(intValue = 36)
                    },
                    require = 1)
    private int modifyInventoryRanges(int constant) {
        return this instanceof IDWSContainer ? constant * 2 : constant;
    }
}
