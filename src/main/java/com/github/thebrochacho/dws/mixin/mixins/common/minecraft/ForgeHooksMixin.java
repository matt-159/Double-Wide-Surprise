package com.github.thebrochacho.dws.mixin.mixins.common.minecraft;

import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ForgeHooks.class)
public class ForgeHooksMixin {
    @ModifyConstant(method = "onPickBlock",
                    constant = @Constant(intValue = 9),
                    remap = false,
                    require = 1)
    private static int modifyHotbarSize(int constant) {
        return 18;
    }
}
