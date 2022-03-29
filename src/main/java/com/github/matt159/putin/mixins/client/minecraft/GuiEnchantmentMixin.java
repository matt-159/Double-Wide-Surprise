package com.github.matt159.putin.mixins.client.minecraft;

import com.github.matt159.putin.Tags;
import com.github.matt159.putin.interfaces.IMinecraftGuiMixin;
import com.github.matt159.putin.util.PutinUtil;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiEnchantment.class)
public class GuiEnchantmentMixin {

    private static final ResourceLocation location = new ResourceLocation(Tags.MODID, "textures/minecraft/enchanting_table.png");
    private static final int X_SIZE = 338;
    private static final int Y_SIZE = 166;

    @Inject(method = "<init>",
            at = @At(   value = "RETURN"),
            require = 1)
    private void updateGuiSize(InventoryPlayer inventoryPlayer, World world, int p_i1090_3_, int p_i1090_4_, int p_i1090_5_, String p_i1090_6_, CallbackInfo ci) {
        ((GuiEnchantment) (Object) (this)).xSize = X_SIZE;
        ((GuiEnchantment) (Object) (this)).ySize = Y_SIZE;
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V",
                            ordinal = 0),
                require = 1)
    private void rerouteBindTexture(TextureManager instance, ResourceLocation resourceLocation) {
        instance.bindTexture(location);
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/gui/GuiEnchantment;drawTexturedModalRect(IIIIII)V",
                            ordinal = 0),
                require = 1)
    private void rerouteDrawCall(GuiEnchantment instance, int x, int y, int u, int v, int w, int h) {
        float zLevel = ((IMinecraftGuiMixin) (Object) (this)).getZLevel();
        PutinUtil.drawTexturedModalRect(x, y, u, v, w, h, zLevel);
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 60),
                    require = 1)
    private int modifyXOffset(int constant) {
        return 141;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
            constant = @Constant(intValue = 62),
            require = 1)
    private int modifyXOffset2(int constant) {
        return 143;
    }
}
