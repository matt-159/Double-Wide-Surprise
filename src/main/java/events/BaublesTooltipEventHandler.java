package events;

import baubles.api.IBauble;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.IBaubleExpanded;
import lombok.val;

import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BaublesTooltipEventHandler {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onItemTooltipEvent(ItemTooltipEvent event) {
        val itemStack = event.itemStack;
        val item = itemStack.getItem();

        String[] types;
        if (item instanceof IBaubleExpanded) {
            types = ((IBaubleExpanded) item).getBaubleTypes(itemStack);


        } else if (item instanceof IBauble) {
            val baubleType = ((IBauble) item).getBaubleType(itemStack);

            val type = BaubleExpandedSlots.getTypeFromBaubleType(baubleType);
            types = new String[] { type };
        } else {
            return;
        }

        val tooltip = new StringBuilder();

        tooltip.append(EnumChatFormatting.GRAY + "[");
        for (int i = 0; i < types.length - 1; i++) {
            tooltip.append(EnumChatFormatting.GOLD + types[i]);
            tooltip.append(EnumChatFormatting.GRAY + "|");
        }

        tooltip.append(EnumChatFormatting.GOLD + types[types.length - 1]);
        tooltip.append(EnumChatFormatting.GRAY + "]");

        event.toolTip.add(tooltip.toString());
    }
}
