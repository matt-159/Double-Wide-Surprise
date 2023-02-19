package com.github.matt159.dws.mixin.mixins.common.chisel;

import net.minecraft.client.gui.inventory.GuiContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.chisel.compat.nei.RecipeHandlerChisel;

@Mixin(RecipeHandlerChisel.class)
public abstract class RecipeHandlerChiselMixin {
    @Inject(method = "getGuiClass",
            at = @At("HEAD"),
            cancellable = true,
            remap = false)
    private void injectRemoveRecipeRectangle(CallbackInfoReturnable<Class<? extends GuiContainer>> cir) {
        cir.setReturnValue(null);
        cir.cancel();
    }
}
