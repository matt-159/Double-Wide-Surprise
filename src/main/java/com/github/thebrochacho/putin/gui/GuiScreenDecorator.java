package com.github.thebrochacho.putin.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class GuiScreenDecorator extends GuiScreen {
    protected final GuiScreen decoratedGui;

    public GuiScreenDecorator(GuiScreen decoratedGui) {
        this.decoratedGui = decoratedGui;

        Field fields[] = this.decoratedGui.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Field f = this.getClass().getDeclaredField(field.getName());
                f.setAccessible(true);
                f.set(this, field.get(decoratedGui));
            } catch (NoSuchFieldException | IllegalAccessException ignored) { }
        }
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        this.decoratedGui.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }


    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    @Override
    protected void keyTyped(char p_73869_1_, int p_73869_2_) {
        try {
            Method keyTyped = this.decoratedGui.getClass().getDeclaredMethod("keyTyped", char.class, int.class);
            keyTyped.invoke(this.decoratedGui, p_73869_1_, p_73869_2_);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void renderToolTip(ItemStack p_146285_1_, int p_146285_2_, int p_146285_3_) {
        try {
            Method keyTyped = this.decoratedGui.getClass().getDeclaredMethod("renderToolTip", ItemStack.class, int.class, int.class);
            keyTyped.invoke(this.decoratedGui, p_146285_1_, p_146285_2_, p_146285_3_);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws the text when mouse is over creative inventory tab. Params: current creative tab to be checked, current
     * mouse x position, current mouse y position.
     */
    @Override
    protected void drawCreativeTabHoveringText(String p_146279_1_, int p_146279_2_, int p_146279_3_) {
        try {
            Method keyTyped = this.decoratedGui.getClass().getDeclaredMethod("drawCreativeTabHoveringText", String.class, int.class, int.class);
            keyTyped.invoke(this.decoratedGui, p_146279_1_, p_146279_2_, p_146279_3_);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void func_146283_a(java.util.List p_146283_1_, int p_146283_2_, int p_146283_3_) {
        try {
            Method keyTyped = this.decoratedGui.getClass().getDeclaredMethod("func_146283_a", List.class, int.class, int.class);
            keyTyped.invoke(this.decoratedGui, p_146283_1_, p_146283_2_, p_146283_3_);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void drawHoveringText(List p_146283_1_, int p_146283_2_, int p_146283_3_, FontRenderer font) {
        try {
            Method keyTyped = this.decoratedGui.getClass().getDeclaredMethod("drawHoveringText", List.class, int.class, int.class, FontRenderer.class);
            keyTyped.invoke(this.decoratedGui, p_146283_1_, p_146283_2_, p_146283_3_, font);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    @Override
    protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
        try {
            Method keyTyped = this.decoratedGui.getClass().getDeclaredMethod("mouseClicked", int.class, int.class, int.class);
            keyTyped.invoke(this.decoratedGui, p_73864_1_, p_73864_2_, p_73864_3_);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
     * mouseMove, which==0 or which==1 is mouseUp
     */
    @Override
    protected void mouseMovedOrUp(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
        try {
            Method keyTyped = this.decoratedGui.getClass().getDeclaredMethod("mouseMovedOrUp", int.class, int.class, int.class);
            keyTyped.invoke(this.decoratedGui, p_146286_1_, p_146286_2_, p_146286_3_);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when a mouse button is pressed and the mouse is moved around. Parameters are : mouseX, mouseY,
     * lastButtonClicked & timeSinceMouseClick.
     */
    @Override
    protected void mouseClickMove(int p_146273_1_, int p_146273_2_, int p_146273_3_, long p_146273_4_) {
        try {
            Method keyTyped = this.decoratedGui.getClass().getDeclaredMethod("mouseClickMove", int.class, int.class, int.class, long.class);
            keyTyped.invoke(this.decoratedGui, p_146273_1_, p_146273_2_, p_146273_3_, p_146273_4_);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void actionPerformed(GuiButton p_146284_1_) {
        try {
            Method keyTyped = this.decoratedGui.getClass().getDeclaredMethod("actionPerformed", GuiButton.class);
            keyTyped.invoke(this.decoratedGui, p_146284_1_);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Causes the screen to lay out its subcomponents again. This is the equivalent of the Java call
     * Container.validate()
     */
    @Override
    public void setWorldAndResolution(Minecraft p_146280_1_, int p_146280_2_, int p_146280_3_) {
        this.decoratedGui.setWorldAndResolution(p_146280_1_, p_146280_2_, p_146280_3_);
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
    public void initGui() {
        this.decoratedGui.initGui();
    }

    /**
     * Delegates mouse and keyboard input.
     */
    @Override
    public void handleInput() {
        this.decoratedGui.handleInput();
    }

    /**
     * Handles mouse input.
     */
    @Override
    public void handleMouseInput() {
        this.decoratedGui.handleMouseInput();
    }

    /**
     * Handles keyboard input.
     */
    @Override
    public void handleKeyboardInput() {
        this.decoratedGui.handleKeyboardInput();
    }

    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen() {
        this.decoratedGui.updateScreen();
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    @Override
    public void onGuiClosed() {
        this.decoratedGui.onGuiClosed();
    }

    /**
     * Draws either a gradient over the background screen (when it exists) or a flat gradient over background.png
     */
    @Override
    public void drawDefaultBackground() {
        this.decoratedGui.drawDefaultBackground();
    }

    @Override
    public void drawWorldBackground(int p_146270_1_) {
        this.decoratedGui.drawWorldBackground(p_146270_1_);
    }

    /**
     * Draws the background (i is always 0 as of 1.2.2)
     */
    @Override
    public void drawBackground(int p_146278_1_) {
        this.decoratedGui.drawBackground(p_146278_1_);
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    @Override
    public boolean doesGuiPauseGame() {
        return this.decoratedGui.doesGuiPauseGame();
    }

    @Override
    public void confirmClicked(boolean p_73878_1_, int p_73878_2_) {
        this.decoratedGui.confirmClicked(p_73878_1_, p_73878_2_);
    }
}
