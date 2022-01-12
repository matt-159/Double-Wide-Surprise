package com.github.matt159.putin.mixins;

import com.github.matt159.putin.gui.GuiScreenDecorator;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;
import java.util.Map;

@Mixin(NetworkRegistry.class)
public abstract class NetworkRegistryMixin {

    @Shadow private Map<ModContainer, IGuiHandler> clientGuiHandlers;

    @Inject(method = "getLocalGuiContainer",
            at = @At("HEAD"),
            cancellable = true,
            remap = false,
            require = 1)
    private void onGetLocalGuiContainer(ModContainer mc, EntityPlayer player, int modGuiId, World world, int x, int y, int z, CallbackInfoReturnable<Object> ci) {
        IGuiHandler handler = clientGuiHandlers.get(mc);
        Object gui = new GuiScreenDecorator((GuiScreen) handler.getClientGuiElement(modGuiId, player, world, x, y, z));
        ci.setReturnValue(gui);
    }
//
//    @Inject(method = "getLocalGuiContainer",
//            at = @At("HEAD"),
//            cancellable = true,
//            remap = false,
//            require = 1)
//    private void onGetLocalGuiContainer(ModContainer mc, EntityPlayer player, int modGuiId, World world, int x, int y, int z, CallbackInfoReturnable<Object> ci) {
//        try {
//            Field f = ((NetworkRegistry)(Object)(this)).getClass().getDeclaredField("clientGuiHandlers");
//            f.setAccessible(true);
//
//            IGuiHandler handler = ((Map<ModContainer, IGuiHandler>)f.get(this)).get(mc);
//            Object gui = new GuiScreenDecorator((GuiScreen) handler.getClientGuiElement(modGuiId, player, world, x, y, z));
//            ci.setReturnValue(gui);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
