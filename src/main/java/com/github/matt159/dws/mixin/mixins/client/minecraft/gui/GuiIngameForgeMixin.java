package com.github.matt159.dws.mixin.mixins.client.minecraft.gui;

import net.minecraftforge.client.GuiIngameForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiIngameForge.class)
public class GuiIngameForgeMixin {

    @ModifyConstant(method = "renderHotbar",
                    constant = @Constant(intValue = 9),
                    remap = false,
                    require = 1)
    private int modifyHotbarSize(int constant) {
        return 18;
    }

    @ModifyConstant(method = "renderHotbar",
                    constant = {    @Constant(intValue = 90),
                                    @Constant(intValue = 91)    },
                    remap = false,
                    require = 1)
    private int modifyHotbarXOffset(int constant) {
        return constant + 90;
    }

    @ModifyConstant(method = "renderHotbar",
                    constant = @Constant(intValue = 182),
                    remap = false,
                    require = 1)
    private int modifyHotbarTextureWidth(int constant) {
        return 362;
    }
}
