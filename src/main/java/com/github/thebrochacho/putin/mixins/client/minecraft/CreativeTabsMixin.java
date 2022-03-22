package com.github.thebrochacho.putin.mixins.client.minecraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SideOnly(Side.CLIENT)
@Mixin(CreativeTabs.class)
public class CreativeTabsMixin {

    @Shadow @Final private int tabIndex;

    @SideOnly(Side.CLIENT)
    @Inject(method = "getTabColumn",
            at = @At("HEAD"),
            cancellable = true)
    private void getTabColumn(CallbackInfoReturnable<Integer> cir) {
        int ret = this.tabIndex > 21 ? (this.tabIndex - 22) % 10 : this.tabIndex % 11;
        cir.setReturnValue(ret);
    }

    @SideOnly(Side.CLIENT)
    @Inject(method = "isTabInFirstRow",
            at = @At("HEAD"),
            cancellable = true)
    private void isTabInFirstRow(CallbackInfoReturnable<Boolean> cir) {
        boolean ret;

        if (this.tabIndex > 21) {
            ret = (this.tabIndex - 22) % 20 < 10;
        } else {
            ret = this.tabIndex < 11;
        }

        cir.setReturnValue(ret);
    }

}
