package com.github.thebrochacho.putin.mixins.client.minecraft;

import com.github.thebrochacho.putin.Tags;
import com.github.thebrochacho.putin.interfaces.IPutinGui;
import com.github.thebrochacho.putin.interfaces.minecraft.IGuiMixin;
import com.github.thebrochacho.putin.util.PutinUtil;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiRepair.class)
public class GuiRepairMixin implements IPutinGui {

    private static final ResourceLocation location = new ResourceLocation(Tags.MODID, "textures/minecraft/anvil.png");
    private static final int X_SIZE = 338;
    private static final int Y_SIZE = 166;

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void updateGuiSize(InventoryPlayer inventoryPlayer, World world, int p_i1073_3_, int p_i1073_4_, int p_i1073_5_, CallbackInfo ci) {
        ((GuiRepair) (Object) (this)).xSize = X_SIZE;
        ((GuiRepair) (Object) (this)).ySize = Y_SIZE;
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
                            target = "Lnet/minecraft/client/gui/GuiRepair;drawTexturedModalRect(IIIIII)V"),
                require = 1)
    private void rerouteDrawCall(GuiRepair instance, int x, int y, int u, int v, int w, int h) {
        float zLevel = ((IGuiMixin) (Object) (this)).getZLevel();
        PutinUtil.drawTexturedModalRect(x, y, u, v, w, h, zLevel);
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 62),
                    require = 1)
    private int modifyTextXOffset(int constant) {
        return 143;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 59),
                    require = 1)
    private int modifyTextFieldXOffset(int constant) {
        return 140;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 99),
                    require = 1)
    private int modifyArrowXOffset(int constant) {
        return 180;
    }

    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 60),
                    require = 1)
    private int modifyDisplayTextXOffset(int constant) {
        return 141;
    }
}
