package com.github.thebrochacho.dws.mixins.client.ironchest;

import com.github.thebrochacho.dws.interfaces.IDWSGui;
import cpw.mods.ironchest.client.GUIChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GUIChest.class)
public abstract class GUIChestMixin extends GuiContainer implements IDWSGui {

    public GUIChestMixin(Container container) {
        super(container);
    }

    @Redirect(  method = "<init>(Lcpw/mods/ironchest/client/GUIChest$GUI;Lnet/minecraft/inventory/IInventory;Lnet/minecraft/inventory/IInventory;)V",
                at = @At(   value = "FIELD",
                            target = "Lcpw/mods/ironchest/client/GUIChest;xSize:I",
                            opcode = Opcodes.PUTFIELD),
                require = 1,
                remap = false)
    private void redirectSetXSize(GUIChest instance, int value) {
        instance.xSize = 338;
    }
}
