package com.github.matt159.putin.util;

import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.IExtendedEntityProperties;

import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ModCompat {
    private static final ConcurrentHashMap<UUID, GCPlayerStats> playerMap = new ConcurrentHashMap<>();

    //Have to go around GCCore's back because reasons
    public static GCPlayerStats getPlayerStats(EntityPlayer player)
    {
        IExtendedEntityProperties IEEP = player.getExtendedProperties(GCPlayerStats.GC_PLAYER_PROP);

        if (IEEP != null) {
            playerMap.put(player.getUniqueID(), (GCPlayerStats) IEEP);
        }

        return playerMap.get(player.getUniqueID());
    }
}
