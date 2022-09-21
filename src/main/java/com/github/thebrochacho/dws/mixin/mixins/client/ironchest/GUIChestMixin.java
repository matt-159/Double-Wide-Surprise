package com.github.thebrochacho.dws.mixin.mixins.client.ironchest;

import com.github.thebrochacho.dws.interfaces.IDWSGui;
import cpw.mods.ironchest.client.GUIChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GUIChest.class)
public abstract class GUIChestMixin extends GuiContainer implements IDWSGui {

    public GUIChestMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>(Lcpw/mods/ironchest/client/GUIChest$GUI;Lnet/minecraft/inventory/IInventory;Lnet/minecraft/inventory/IInventory;)V",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private void injectNewXSize(GUIChest.GUI type, IInventory player, IInventory chest, CallbackInfo ci) {
        this.xSize = 338;
    }
}
