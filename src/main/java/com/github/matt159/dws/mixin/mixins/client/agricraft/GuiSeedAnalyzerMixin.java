package com.github.matt159.dws.mixin.mixins.client.agricraft;

import com.InfinityRaider.AgriCraft.gui.GuiSeedAnalyzer;
import com.InfinityRaider.AgriCraft.tileentity.TileEntitySeedAnalyzer;
import com.github.matt159.dws.util.Constants;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiSeedAnalyzer.class)
public abstract class GuiSeedAnalyzerMixin extends GuiContainer {
    public GuiSeedAnalyzerMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    private void injectNewXSize(InventoryPlayer inventory, TileEntitySeedAnalyzer seedAnalyzer, CallbackInfo ci) {
        this.xSize = Constants.GENERAL_DWS_GUI_WIDTH;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 131),
                    require = 1)
    private int modifyButtonXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 68),
                    require = 1)
    private int modifyProgressBarXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
