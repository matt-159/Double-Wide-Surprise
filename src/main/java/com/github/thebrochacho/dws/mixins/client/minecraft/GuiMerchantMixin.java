package com.github.thebrochacho.dws.mixins.client.minecraft;

import com.github.thebrochacho.dws.Tags;
import com.github.thebrochacho.dws.interfaces.IDWSGui;
import com.github.thebrochacho.dws.interfaces.minecraft.IGuiMixin;
import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMerchant.class)
public class GuiMerchantMixin implements IDWSGui {

    private static final ResourceLocation location = new ResourceLocation(Tags.MODID, "textures/minecraft/villager.png");
    private static final int X_SIZE = 338;
    private static final int Y_SIZE = 166;

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void updateGuiSize(InventoryPlayer inventoryPlayer, IMerchant merchant, World world, String p_i1096_4_, CallbackInfo ci) {
        ((GuiMerchant) (Object) (this)).xSize = X_SIZE;
        ((GuiMerchant) (Object) (this)).ySize = Y_SIZE;
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V"),
                require = 1)
    private void rerouteBindTexture(TextureManager instance, ResourceLocation resourceLocation) {
        instance.bindTexture(location);
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/gui/GuiMerchant;drawTexturedModalRect(IIIIII)V"),
                require = 1)
    private void rerouteDrawCall(GuiMerchant instance, int x, int y, int u, int v, int w, int h) {
        float zLevel = ((IGuiMixin) (Object) (this)).getZLevel();
        DWSUtil.drawTexturedModalRect(x, y, u, v, w, h, zLevel);
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
