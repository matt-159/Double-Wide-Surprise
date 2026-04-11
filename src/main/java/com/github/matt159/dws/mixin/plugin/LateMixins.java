package com.github.matt159.dws.mixin.plugin;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

@LateMixin
public class LateMixins implements ILateMixinLoader {
    @Override
    public String getMixinConfig() {
        return "mixins.dws.late.json";
    }

    @Override
    public @NotNull List<String> getMixins(Set<String> loadedMods) {
        return IMixins.getLateMixins(Mixin.class, loadedMods);
    }
}
