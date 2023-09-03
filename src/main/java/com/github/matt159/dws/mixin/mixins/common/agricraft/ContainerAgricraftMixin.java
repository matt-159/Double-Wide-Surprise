package com.github.matt159.dws.mixin.mixins.common.agricraft;

import com.InfinityRaider.AgriCraft.container.ContainerAgricraft;
import com.github.matt159.dws.interfaces.IDWSContainer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerAgricraft.class)
public abstract class ContainerAgricraftMixin extends Container implements IDWSContainer {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
