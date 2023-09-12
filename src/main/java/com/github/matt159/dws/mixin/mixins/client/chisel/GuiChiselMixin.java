package com.github.matt159.dws.mixin.mixins.client.chisel;

import com.github.matt159.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import team.chisel.client.gui.GuiChisel;
import team.chisel.inventory.InventoryChiselSelection;

@Mixin(GuiChisel.class)
public abstract class GuiChiselMixin extends GuiContainer implements IDWSGui {
    public GuiChiselMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private void injectNewXYSize(InventoryPlayer iinventory, InventoryChiselSelection menu, CallbackInfo ci) {
        this.xSize = 338;
        this.ySize = 166;
    }

    @Redirect(method = "drawGuiContainerBackgroundLayer",
              at = @At(value = "INVOKE",
                       target = "Lteam/chisel/client/gui/GuiChisel;func_146110_a(IIFFIIFF)V",
                       ordinal = 0),
              require = 1)
    private void redirectBackgroundDrawCall1(int x, int y, float u, float v, int w, int h, float textureWidth, float textureHeight) {
        func_146110_a(x, y, 0, 0, this.xSize, this.ySize, 338, 198);
    }

    @Redirect(method = "drawGuiContainerBackgroundLayer",
            at = @At(value = "INVOKE",
                    target = "Lteam/chisel/client/gui/GuiChisel;func_146110_a(IIFFIIFF)V",
                    ordinal = 1),
            require = 1)
    private void redirectBackgroundDrawCall2(int x, int y, float u, float v, int w, int h, float textureWidth, float textureHeight) {
        func_146110_a(x, y, 0, 166, 32, 32, 338, 198);
    }

    @Redirect(method = "drawGuiContainerForegroundLayer",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/client/gui/FontRenderer;drawSplitString(Ljava/lang/String;IIII)V"),
              require = 1)
    private void redirectDrawSplitString(FontRenderer instance, String string, int p_78279_2_, int p_78279_3_, int p_78279_4_, int p_78279_5_) {}

    @Redirect(method = "drawGuiContainerForegroundLayer",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;III)I"),
              require = 1)
    private int redirectDrawString(FontRenderer instance, String string, int p_78276_2_, int p_78276_3_, int p_78276_4_) {
        return 0;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 12),
                    require = 1)
    private int modifyButtonXOffset(int constant) {
        return 8;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 132),
                    require = 1)
    private int modifyButtonYOffset(int constant) {
        return 44;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 246),
                    require = 1)
    private int modifyScrollbarXOffset(int constant) {
        return 318;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 106),
                    require = 1)
    private int modifyScrollbarHeight(int constant) {
        return 70;
    }

    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 12),
                    require = 1)
    private int modifyTextXOffset(int constant) {
        return 8;
    }

    @ModifyConstant(method = "func_146977_a",
                    constant = @Constant(intValue = 16),
                    require = 4)
    private int modifyDisplayPositionOffsets(int constant) {
        return 12;
    }
}
