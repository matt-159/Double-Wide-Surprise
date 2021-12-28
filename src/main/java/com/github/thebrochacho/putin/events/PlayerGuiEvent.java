package com.github.thebrochacho.putin.events;

import com.github.thebrochacho.putin.Putin;
import com.github.thebrochacho.putin.gui.PutinGui;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.lang.reflect.Field;
import java.util.List;

public class PlayerGuiEvent {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onPlayerOpenGUI(GuiOpenEvent event) {
        if (event.gui != null && event.gui.getClass() == GuiInventory.class && !(event.gui instanceof PutinGui)) {
            event.setCanceled(true);

            FMLNetworkHandler.openGui(Minecraft.getMinecraft().thePlayer, Putin.INSTANCE, 1, Minecraft.getMinecraft().theWorld, 0, 0, 0);
        }
    }
}
