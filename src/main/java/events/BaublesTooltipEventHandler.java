package events;

import baubles.api.IBauble;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.IBaubleExpanded;
import baubles.common.lib.PlayerHandler;
import com.github.matt159.dws.util.MixAndMatchSlotUtil;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
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

        val lShift = Keyboard.KEY_LSHIFT;

        if (!Keyboard.isKeyDown(lShift)) {
            event.toolTip.add("Hold [" + Keyboard.getKeyName(lShift) + "] for more info");
        } else {
            int maxLength = -1;
            for (int i = 0; i < event.toolTip.size(); i++) {
                val length = event.toolTip.get(i).length();
                if (length > maxLength) {
                    maxLength = length;
                }
            }

            event.toolTip.add(EnumChatFormatting.WHITE + StringUtils.repeat("-", maxLength));
            event.toolTip.add(EnumChatFormatting.GRAY + "Available slots");

            val baubles = PlayerHandler.getPlayerBaubles(event.entityPlayer);

            val availableTags = MixAndMatchSlotUtil.getAvailableTags(baubles);

            for (val entry : availableTags.entrySet()) {
                event.toolTip.add(EnumChatFormatting.GRAY + "[" + EnumChatFormatting.GOLD + entry.getKey() + EnumChatFormatting.GRAY + "]: " + entry.getValue());
            }
        }
    }
}
