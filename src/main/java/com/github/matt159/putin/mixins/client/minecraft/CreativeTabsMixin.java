package com.github.matt159.putin.mixins.client.minecraft;

import com.github.matt159.putin.interfaces.ICreativeTabsMixin;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SideOnly(Side.CLIENT)
@Mixin(CreativeTabs.class)
public class CreativeTabsMixin implements ICreativeTabsMixin {

    @Mutable
    @Shadow @Final private int tabIndex;

    @Shadow @Final public static CreativeTabs tabBlock;

    @Shadow @Final public static CreativeTabs tabAllSearch;

    @Shadow @Final public static CreativeTabs tabDecorations;

    @Shadow @Final public static CreativeTabs tabInventory;

    @Shadow public static CreativeTabs[] creativeTabArray;

    @SideOnly(Side.CLIENT)
    @Inject(method = "<clinit>",
                    at = @At(value = "RETURN"),
                    require = 1)
    private static void adjustTabs(CallbackInfo ci) {

        ((ICreativeTabsMixin) tabBlock).setTabIndex(5);
        ((ICreativeTabsMixin) tabAllSearch).setTabIndex(0);

        ((ICreativeTabsMixin) tabDecorations).setTabIndex(11);
        ((ICreativeTabsMixin) tabInventory).setTabIndex(1);

        creativeTabArray[tabAllSearch.getTabIndex()] = CreativeTabs.tabAllSearch;
        creativeTabArray[tabInventory.getTabIndex()] = CreativeTabs.tabInventory;

        creativeTabArray[tabBlock.getTabIndex()] = CreativeTabs.tabBlock;
        creativeTabArray[tabDecorations.getTabIndex()] = CreativeTabs.tabDecorations;
    }

    /*  Vanilla uses some wonky modulus math to place the search tab and the survival inventory tab
        I chose to simplify it by moving those two tabs to the front of the creativeTabArray
     */
    @SideOnly(Side.CLIENT)
    @Inject(method = "getTabColumn",
            at = @At("HEAD"),
            cancellable = true)
    private void getTabColumn(CallbackInfoReturnable<Integer> cir) {
        int ret = this.tabIndex > 1 ? (tabIndex - 2) % 10 : 10;
        cir.setReturnValue(ret);
    }

    @SideOnly(Side.CLIENT)
    @Inject(method = "isTabInFirstRow",
            at = @At("HEAD"),
            cancellable = true)
    private void isTabInFirstRow(CallbackInfoReturnable<Boolean> cir) {
        boolean ret = this.tabIndex > 1 ? (this.tabIndex - 2) % 20 < 10 : this.tabIndex % 2 == 0;
        cir.setReturnValue(ret);
    }

    @SideOnly(Side.CLIENT)
    @Inject(method = "getTabPage",
            at = @At("HEAD"),
            remap = false,
            cancellable = true)
    private void getTabPage(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(this.tabIndex > 21 ? (this.tabIndex - 22) / 20 + 1 : 0);
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }
}
