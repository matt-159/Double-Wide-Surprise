package com.github.thebrochacho.dws.mixin.mixins.client.minecraft.gui;

import com.github.thebrochacho.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntityBeacon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiBeacon.class)
public abstract class GuiBeaconMixin extends GuiContainer implements IDWSGui {

    public GuiBeaconMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void updateGuiSize(InventoryPlayer inventoryPlayer, TileEntityBeacon tileEntityBeacon, CallbackInfo ci) {
        this.xSize = 338;
        this.ySize = 219;
    }

    @ModifyConstant(method = {  "initGui",
                                "updateScreen",
                                "drawGuiContainerBackgroundLayer",
                                "drawGuiContainerForegroundLayer"   },
                    constant = {    @Constant(intValue = 42),
                                    @Constant(intValue = 62),
                                    @Constant(intValue = 76),
                                    @Constant(intValue = 164),
                                    @Constant(intValue = 167),
                                    @Constant(intValue = 169),
                                    @Constant(intValue = 190)   },
                    require = 1)
    private int modifyXOffset(int constant) {
        return constant + 54;
    }
}
