package com.github.matt159.dws.mixin.mixins.client.chisel;

import net.minecraft.client.gui.GuiButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.chisel.client.gui.GuiChisel;
import team.chisel.client.gui.widgets.GuiButtonChiselMode;

@Mixin(GuiButtonChiselMode.class)
public abstract class GuiButtonChiselModeMixin extends GuiButton {
    public GuiButtonChiselModeMixin(int id, int x, int y, String displayString) {
        super(id, x, y, displayString);
    }

    @ModifyConstant(method = { "drawButtonForegroundLayer",
                               "drawButtonBackgroundLayer" },
                    constant = @Constant(floatValue = 266.0F),
                    remap = false,
                    require = 2)
    private float modifyTextureWidth(float constant) {
        return 338F;
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private void injectNewDimensions(int id, int x, int y, GuiChisel guiChisel, CallbackInfo ci) {
        this.width = 32;
        this.height = 32;
    }

    @Redirect(method = "drawButtonBackgroundLayer",
              at = @At(value = "INVOKE",
                       target = "Lteam/chisel/client/gui/widgets/GuiButtonChiselMode;func_146110_a(IIFFIIFF)V"),
              remap = false,
              require = 1)
    private void redirectBackgroundDrawCall(int x, int y, float u, float v, int buttonWidth, int buttonHeight, float textureWidth, float textureHeight) {
        u = 32 + 32 * (this.field_146123_n ? 1 : 0);
        v = 166;
        func_146110_a(x, y, u, v, this.width, this.height, 338, 198);
    }

    @ModifyConstant(method = "drawButtonForegroundLayer",
                    constant = @Constant(intValue = 3),
                    remap = false,
                    require = 2)
    private int modifyVignetteOffset(int constant) {
        return 4;
    }

    @ModifyConstant(method = "drawButtonForegroundLayer",
                    constant = @Constant(intValue = 14),
                    remap = false,
                    require = 8)
    private int modifyVignetteDimensions(int constant) {
        return 8;
    }

    @ModifyConstant(method = "drawButtonForegroundLayer",
                    constant = @Constant(floatValue = 14.0F),
                    remap = false,
                    require = 1)
    private float modifyVignetteUOffset(float constant) {
        return 8;
    }

    @ModifyConstant(method = "drawButtonForegroundLayer",
                    constant = @Constant(floatValue = 147F),
                    remap = false,
                    require = 1)
    private float modifyVignetteBaseU(float constant) {
        return 100F;
    }

    @ModifyConstant(method = "drawButtonForegroundLayer",
                    constant = @Constant(floatValue = 219F),
                    remap = false,
                    require = 2)
    private float modifyVignetteV1(float constant) {
        return 178F;
    }

    @ModifyConstant(method = "drawButtonForegroundLayer",
                    constant = @Constant(floatValue = 205F),
                    remap = false,
                    require = 2)
    private float modifyVignetteV2(float constant) {
        return 170F;
    }

    @ModifyConstant(method = "drawButtonForegroundLayer",
                    constant = @Constant(intValue = 48),
                    remap = false,
                    require = 1)
    private int modifyButtonWidth(int constant) {
        return 32;
    }

    @ModifyConstant(method = "drawButtonForegroundLayer",
                    constant = @Constant(intValue = 42),
                    remap = false,
                    require = 4)
    private int modifyStackedVignetteSize(int constant) {
        return 24;
    }

    @Redirect(method = "drawButtonForegroundLayer",
              at = @At(value = "INVOKE",
                       target = "Lteam/chisel/client/gui/widgets/GuiButtonChiselMode;func_146110_a(IIFFIIFF)V"),
              remap = false,
              require = 1)
    private void redirectForegroundDrawCall(int x, int y, float u, float v, int w, int h, float textureWidth, float textureHeight) {
        func_146110_a(x, y, u, v, w, h, 338, 198);
    }
}
