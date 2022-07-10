package com.github.matt159.dws.mixins.client.minecraft;

import com.github.matt159.dws.Tags;
import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.interfaces.minecraft.IGuiMixin;
import com.github.matt159.dws.util.DWSUtil;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
