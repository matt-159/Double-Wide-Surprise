package com.github.matt159.dws.mixin.mixins.common.backpack;

import com.github.matt159.dws.interfaces.IDWSContainer;
import de.eydamos.backpack.inventory.container.ContainerAdvanced;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ContainerAdvanced.class)
public class ContainerAdvancedMixin implements IDWSContainer {
}
