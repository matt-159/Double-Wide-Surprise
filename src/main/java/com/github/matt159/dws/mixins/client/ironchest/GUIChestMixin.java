package com.github.matt159.dws.mixins.client.ironchest;

import com.github.matt159.dws.Tags;
import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.util.DWSUtil;
import cpw.mods.ironchest.client.GUIChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GUIChest.class)
public abstract class GUIChestMixin extends GuiContainer implements IDWSGui {

    private static final String PATH = "textures/ironchest/";

    public GUIChestMixin(Container container) {
        super(container);
    }

    private GUIChest.GUI type;

    @Inject(method = "<init>(Lcpw/mods/ironchest/client/GUIChest$GUI;Lnet/minecraft/inventory/IInventory;Lnet/minecraft/inventory/IInventory;)V",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private void determineGuiInfo(GUIChest.GUI type, IInventory player, IInventory chest, CallbackInfo ci) {
        xSize = 338;

        this.type = type;
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V"),
                require = 1)
    private void redirectBindTexture(TextureManager instance, ResourceLocation resourceLocation) {
        instance.bindTexture(getResourceLocation());
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lcpw/mods/ironchest/client/GUIChest;drawTexturedModalRect(IIIIII)V"),
                require = 1)
    private void redirectDrawCall(GUIChest instance, int x, int y, int u, int v, int w, int h) {
        DWSUtil.drawTexturedModalRect(x, y, u, v, w, h, zLevel);
    }

    private ResourceLocation getResourceLocation() {
        String chestType;

        switch (type.name()) {
            case "IRON":
                chestType = "iron";
                break;
            case "COPPER":
                chestType = "copper";
                break;
            case "GOLD":
                chestType = "gold";
                break;
            case "SILVER":
            case "STEEL":
                chestType = "silver";
                break;
            case "DIAMOND":
            case "CRYSTAL":
            case "OBSIDIAN":
                chestType = "diamond";
                break;
            case "DIRTCHEST9000":
                chestType = "dirt";
                break;
            default:
                chestType = "";
                break;
        }

        chestType += "_chest.png";

        return new ResourceLocation(Tags.MODID, PATH + chestType);
    }
}
