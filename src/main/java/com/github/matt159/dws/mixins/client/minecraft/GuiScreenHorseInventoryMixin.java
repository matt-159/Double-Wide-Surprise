package com.github.matt159.dws.mixins.client.minecraft;

import com.github.matt159.dws.Tags;
import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.interfaces.minecraft.IGuiMixin;
import com.github.matt159.dws.util.DWSUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreenHorseInventory.class)
public abstract class GuiScreenHorseInventoryMixin extends GuiContainer implements IDWSGui {

    @Shadow private EntityHorse field_147034_x;
    @Shadow private float field_147033_y;
    @Shadow private float field_147032_z;
    private static final ResourceLocation location = new ResourceLocation(Tags.MODID, "textures/minecraft/gui/container/horse.png");
    private static final int X_SIZE = 338;
    private static final int Y_SIZE = 166;

    public GuiScreenHorseInventoryMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void updateGuiSize(IInventory inventoryPlayer, IInventory inventoryHorse, EntityHorse entityHorse, CallbackInfo ci) {
        ((GuiScreenHorseInventory) (Object) (this)).xSize = X_SIZE;
        ((GuiScreenHorseInventory) (Object) (this)).ySize = Y_SIZE;
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V"),
                require = 1)
    private void rerouteBindTexture(TextureManager instance, ResourceLocation p_bindTexture_1_) {
        instance.bindTexture(location);
    }

    @Inject(method = "drawGuiContainerBackgroundLayer",
            at = @At(   value = "INVOKE",
                        target = "Lnet/minecraft/client/gui/inventory/GuiScreenHorseInventory;drawTexturedModalRect(IIIIII)V",
                        ordinal = 0,
                        shift = At.Shift.BEFORE),
            cancellable = true,
            require = 1)
    private void rerouteDrawCall(float f1, int i1, int i2, CallbackInfo ci) {
        GuiScreenHorseInventory gshi = (GuiScreenHorseInventory) (Object) (this);

        int x = (gshi.width - gshi.xSize) / 2;
        int y = (gshi.height - gshi.ySize) / 2;
        DWSUtil.drawTexturedModalRect(x, y, 0, 0, gshi.xSize, gshi.ySize, zLevel);

        if (this.field_147034_x.isChested()) {
            DWSUtil.drawTexturedModalRect(x + 160, y + 17, 0, gshi.ySize, 90, 54, zLevel);
        }

        if (this.field_147034_x.func_110259_cr()) {
            DWSUtil.drawTexturedModalRect(x + 88, y + 35, 0, gshi.ySize + 54, 18, 18, zLevel);
        }

        GuiInventory.func_147046_a(x + 132, y + 60, 17, (float)(x + 132) - this.field_147033_y, (float)(y + 75 - 50) - this.field_147032_z, this.field_147034_x);

        ci.cancel();
    }
}
