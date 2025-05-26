package com.github.matt159.dws.mixin.plugin;

import com.falsepattern.lib.mixin.IMixin;
import com.falsepattern.lib.mixin.IMixinPlugin;
import com.falsepattern.lib.mixin.ITargetedMod;
import lombok.Getter;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;

import static com.github.matt159.dws.Tags.MOD_NAME;


/**
 * The {@link IMixinConfigPlugin} implementation, delegating most of the logic to FalsePatternLib.
 */
public final class MixinPlugin implements IMixinPlugin {
    /**
     * Logger provided by FalsePatternLib.
     */
    @Getter
    private final Logger logger = IMixinPlugin.createLogger(MOD_NAME);

    /**
     * Invoked by FalsePattern Lib to decide what targeted mods to check for.
     *
     * @return targeted mods
     */
    @Override
    public ITargetedMod[] getTargetedModEnumValues() {
        return TargetedMod.values();
    }

    /**
     * Invoked by FalsePattern Lib to decide what mixins to load.
     *
     * @return mixins to load
     */
    @Override
    public IMixin[] getMixinEnumValues() {
        return Mixin.values();
    }
}
