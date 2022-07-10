package com.github.matt159.dws.mixins.client.minecraft;

import com.github.matt159.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiMerchant.class)
public abstract class GuiMerchantMixin extends GuiContainer implements IDWSGui {

    public GuiMerchantMixin(Container container) {
        super(container);
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 120),
                    require = 1)
    private int modifyLeftButtonXOffset(int constant) {
        return 201;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 36),
                    require = 1)
    private int modifyRightButtonXOffset(int constant) {
        return 117;
    }

    @ModifyConstant(method = "drawScreen",
                    constant = @Constant(intValue = 36),
                    require = 1)
    private int modifyLeftItemXOffset(int constant) {
        return 117;
    }

    @ModifyConstant(method = "drawScreen",
                    constant = @Constant(intValue = 62),
                    require = 1)
    private int modifyRightItemXOffset(int constant) {
        return 143;
    }

    @ModifyConstant(method = "drawScreen",
                    constant = @Constant(intValue = 120),
                    require = 1)
    private int modifyResultItemXOffset(int constant) {
        return 201;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 83),
                    require = 1)
    private int modifyArrowXOffset(int constant) {
        return 164;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 212),
                    require = 1)
    private int modifyArrowTextureXOffset(int constant) {
        return 374;
    }
}
