package com.github.matt159.dws.mixin.mixins.common.nei;

import codechicken.nei.recipe.TemplateRecipeHandler;
import com.github.matt159.dws.util.ListOfGUIsWithTransferRects;
import net.minecraft.client.gui.inventory.GuiContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.*;
import java.util.Collection;
import java.util.List;

@Mixin(TemplateRecipeHandler.class)
public abstract class TemplateRecipeHandlerMixin {
//    @ModifyConstant(method = "drawBackground",
//                    constant = @Constant(intValue = 5),
//                    remap = false,
//                    require = 1)
//    private int modifyBackgroundXOffset(int constant) {
//        return constant + 81;
//    }

    private static Class<? extends GuiContainer> clazz = null;

    @Inject(method = "transferRect(Lnet/minecraft/client/gui/inventory/GuiContainer;Ljava/util/Collection;IIZ)Z",
            at = @At("HEAD"),
            remap = false,
            require = 1)
    private static void injectCaptureGuiContainerClass(GuiContainer gui,
                                                       Collection<TemplateRecipeHandler.RecipeTransferRect> transferRects,
                                                       int offsetx,
                                                       int offsety,
                                                       boolean usage,
                                                       CallbackInfoReturnable<Boolean> cir) {
        clazz = gui.getClass();
    }

    @Inject(method = "transferRectTooltip",
            at = @At("HEAD"),
            remap = false,
            require = 1)
    private static void injectCaptureGuiContainerClass(GuiContainer gui,
                                                       Collection<TemplateRecipeHandler.RecipeTransferRect> transferRects,
                                                       int offsetx,
                                                       int offsety,
                                                       List<String> currenttip,
                                                       CallbackInfoReturnable<List<String>> cir) {
        clazz = gui.getClass();
    }

    @SuppressWarnings("rawtypes")
    @Redirect(method = { "transferRect(Lnet/minecraft/client/gui/inventory/GuiContainer;Ljava/util/Collection;IIZ)Z",
                         "transferRectTooltip" },
              at = @At(value = "INVOKE",
                       target = "Ljava/awt/Rectangle;contains(Ljava/awt/Point;)Z"),
              remap = false,
              require = 2)
    private static boolean redirectNextRectangle(Rectangle instance, Point point) {
        if (ListOfGUIsWithTransferRects.contains(clazz)) {
            Rectangle rect = new Rectangle(instance);

            rect.x += 81;

            return rect.contains(point);
        }

        return instance.contains(point);
    }

    @Inject(method = "transferRect(Lnet/minecraft/client/gui/inventory/GuiContainer;Ljava/util/Collection;IIZ)Z",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private static void injectReleaseGuiContainerClass(GuiContainer gui,
                                                       Collection<TemplateRecipeHandler.RecipeTransferRect> transferRects,
                                                       int offsetx,
                                                       int offsety,
                                                       boolean usage,
                                                       CallbackInfoReturnable<Boolean> cir) {
        clazz = null;
    }

    @Inject(method = "transferRectTooltip",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private static void injectReleaseGuiContainerClass(GuiContainer gui,
                                                       Collection<TemplateRecipeHandler.RecipeTransferRect> transferRects,
                                                       int offsetx,
                                                       int offsety,
                                                       List<String> currenttip,
                                                       CallbackInfoReturnable<List<String>> cir) {
        clazz = null;
    }
}
