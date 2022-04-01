package com.github.thebrochacho.putin.mixins.client.minecraft;

import com.github.thebrochacho.putin.Tags;
import com.github.thebrochacho.putin.interfaces.IPutinGui;
import com.github.thebrochacho.putin.interfaces.minecraft.IGuiMixin;
import com.github.thebrochacho.putin.util.PutinUtil;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreenHorseInventory.class)
public class GuiScreenHorseInventoryMixin implements IPutinGui {

    @Shadow private EntityHorse field_147034_x;
    @Shadow private float field_147033_y;
    @Shadow private float field_147032_z;
    private static final ResourceLocation location = new ResourceLocation(Tags.MODID, "textures/minecraft/horse.png");
    private static final int X_SIZE = 338;
    private static final int Y_SIZE = 166;

    @Inject(method = "<init>",
            at = @At(   value = "RETURN",
                        remap = false),
            require = 1)
    private void updateGuiSize(IInventory p_i1817_1_, IInventory p_i1817_2_, EntityHorse p_i1817_3_, CallbackInfo ci) {
        ((GuiScreenHorseInventory) (Object) (this)).xSize = X_SIZE;
        ((GuiScreenHorseInventory) (Object) (this)).ySize = Y_SIZE;
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V",
                            remap = false),
                require = 1)
    private void rerouteBindTexture(TextureManager instance, ResourceLocation p_bindTexture_1_) {
        instance.bindTexture(location);
    }

    @Inject(method = "drawGuiContainerBackgroundLayer",
            at = @At(   value = "INVOKE",
                        target = "Lnet/minecraft/client/gui/inventory/GuiScreenHorseInventory;drawTexturedModalRect(IIIIII)V",
                        ordinal = 0,
                        shift = At.Shift.BEFORE,
                        remap = false),
            cancellable = true,
            require = 1)
    private void rerouteDrawCall(float p_drawGuiContainerBackgroundLayer_1_, int p_drawGuiContainerBackgroundLayer_2_, int p_drawGuiContainerBackgroundLayer_3_, CallbackInfo ci) {
        GuiScreenHorseInventory gshi = (GuiScreenHorseInventory) (Object) (this);
        float zLevel = ((IGuiMixin) (Object) (this)).getZLevel();

        int x = (gshi.width - gshi.xSize) / 2;
        int y = (gshi.height - gshi.ySize) / 2;
        PutinUtil.drawTexturedModalRect(x, y, 0, 0, gshi.xSize, gshi.ySize, zLevel);

        if (this.field_147034_x.isChested()) {
            PutinUtil.drawTexturedModalRect(x + 160, y + 17, 0, gshi.ySize, 90, 54, zLevel);
        }

        if (this.field_147034_x.func_110259_cr()) {
            PutinUtil.drawTexturedModalRect(x + 88, y + 35, 0, gshi.ySize + 54, 18, 18, zLevel);
        }

        GuiInventory.func_147046_a(x + 132, y + 60, 17, (float)(x + 132) - this.field_147033_y, (float)(y + 75 - 50) - this.field_147032_z, this.field_147034_x);

        ci.cancel();
    }
}
