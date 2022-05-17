package com.github.matt159.dws.mixins.common.minecraft;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @ModifyConstant(method="func_147112_ai",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private int modifyHotbarOffset(int constant) {
        return 18;
    }
}
