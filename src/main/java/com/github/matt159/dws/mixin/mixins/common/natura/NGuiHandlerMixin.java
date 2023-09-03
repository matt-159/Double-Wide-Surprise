package com.github.matt159.dws.mixin.mixins.common.natura;

import cpw.mods.fml.common.network.IGuiHandler;
import lombok.val;
import mods.natura.blocks.tech.NetherrackFurnaceLogic;
import mods.natura.gui.NGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NGuiHandler.class)
public abstract class NGuiHandlerMixin implements IGuiHandler {
    @Inject(method = "getServerGuiElement",
            at = @At(value = "RETURN", ordinal = 1),
            cancellable = true,
            require = 1,
            remap = false)
    private void injectReturn(int ID, EntityPlayer player, World world, int x, int y, int z, CallbackInfoReturnable<Object> cir) {
        val furnaceTE = (NetherrackFurnaceLogic) world.getTileEntity(x, y, z);
        Object returnValue = null;

        if (ID == 2) {
            returnValue = new ContainerFurnace(player.inventory, furnaceTE);
        }

        cir.setReturnValue(returnValue);
    }
}
