package com.github.matt159.dws.events;

import com.github.matt159.dws.gui.minecraft.DWSHotbar;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;

public class RenderGameOverlayEvents {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void guiPreAction(RenderGameOverlayEvent.Pre event) {
        if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR) {
            event.setCanceled(true);

            DWSHotbar hotbar = new DWSHotbar();

            hotbar.renderDWSHotbar(event.partialTicks, event.resolution);

            MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Post(event, RenderGameOverlayEvent.ElementType.HOTBAR));
        }
    }
}
