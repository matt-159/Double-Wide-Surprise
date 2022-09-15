package com.github.matt159.dws.mixin.mixins.client.minecraft.gui;

import com.github.matt159.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.transformer.meta.MixinInner;

@Mixin(GuiMerchant.class)
public abstract class GuiMerchantMixin extends GuiContainer implements IDWSGui {

    public GuiMerchantMixin(Container container) {
        super(container);
    }

    @ModifyConstant(method = {  "initGui",
                                "drawScreen",
                                "drawGuiContainerBackgroundLayer"   },
                    constant = {    @Constant(intValue = 36),
                                    @Constant(intValue = 62),
                                    @Constant(intValue = 83),
                                    @Constant(intValue = 120)   },
                    require = 1)
    private int modifyXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 212),
                    require = 1)
    private int modifyArrowTextureXOffset(int constant) {
        return 374;
    }
}
