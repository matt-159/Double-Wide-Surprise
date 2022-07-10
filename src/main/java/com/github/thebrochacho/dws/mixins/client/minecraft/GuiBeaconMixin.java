package com.github.thebrochacho.dws.mixins.client.minecraft;

import com.github.thebrochacho.dws.Tags;
import com.github.thebrochacho.dws.interfaces.IDWSGui;
import com.github.thebrochacho.dws.interfaces.minecraft.IGuiMixin;
import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
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

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 164),
                    require = 1)
    private int modifyConfirmButtonXOffset(int constant) {
        return 219;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 190),
                    require = 1)
    private int modifyCancelButtonXOffset(int constant) {
        return 244;
    }

    @ModifyConstant(method = "updateScreen",
                    constant = @Constant(intValue = 76),
                    require = 1)
    private int modifyPrimaryButtonXOffset(int constant) {
        return 130;
    }

    @ModifyConstant(method = "updateScreen",
                    constant = @Constant(intValue = 167),
                    require = 1)
    private int modifySecondaryButtonXOffset(int constant) {
        return 221;
    }

    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
            constant = @Constant(intValue = 62),
            require = 1)
    private int modifyBeaconPrimaryStringXOffset(int constant) {
        return 116;
    }

    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 169),
                    require = 1)
    private int modifyBeaconSecondaryStringXOffset(int constant) {
        return 223;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 42),
                    require = 1)
    private int modifyItemRenderXOffset(int constant) {
        return 96;
    }
}
