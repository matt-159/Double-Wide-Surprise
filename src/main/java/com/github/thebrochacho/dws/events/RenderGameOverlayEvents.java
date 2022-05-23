package com.github.thebrochacho.dws.events;

import com.github.thebrochacho.dws.gui.minecraft.DWSHotbar;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;

public class RenderGameOverlayEvents {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void guiPreAction(RenderGameOverlayEvent.Pre event) {
        if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR) {
            event.setCanceled(true);

            DWSHotbar hotbar = new DWSHotbar();

            hotbar.renderDWSHotbar(event.partialTicks, event.resolution);

            MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Post(event, RenderGameOverlayEvent.ElementType.HOTBAR));
        }
    }
}
