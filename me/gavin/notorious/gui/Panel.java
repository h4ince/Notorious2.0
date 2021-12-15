// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.gui;

import net.minecraft.client.Minecraft;
import java.awt.Color;
import net.minecraft.client.gui.Gui;
import me.gavin.notorious.util.ColorUtil;
import me.gavin.notorious.hack.hacks.client.Font;
import me.gavin.notorious.hack.hacks.client.ClickGUI;
import org.lwjgl.input.Mouse;
import java.util.Iterator;
import me.gavin.notorious.Notorious;
import me.gavin.notorious.hack.Hack;
import me.gavin.notorious.gui.api.AbstractToggleContainer;
import java.util.ArrayList;
import me.gavin.notorious.gui.api.AbstractDragComponent;

public class Panel extends AbstractDragComponent
{
    private final ArrayList<AbstractToggleContainer> buttons;
    private final Hack.Category category;
    
    public Panel(final Hack.Category category, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.buttons = new ArrayList<AbstractToggleContainer>();
        this.category = category;
        for (final Hack hack : Notorious.INSTANCE.hackManager.getHacksFromCategory(category)) {
            this.buttons.add(new Button(hack, x, y, width, 10));
        }
    }
    
    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        if (Mouse.getEventDWheel() > 0) {
            this.y -= (int)Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).scrollSpeed.getValue();
        }
        else if (Mouse.getEventDWheel() < 0) {
            this.y += (int)Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).scrollSpeed.getValue();
        }
        final Font font = Notorious.INSTANCE.hackManager.getHack(Font.class);
        final float time = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).length.getValue();
        final float saturation = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).saturation.getValue();
        int color;
        if (Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).colorMode.getMode().equals("Rainbow")) {
            color = ColorUtil.getRainbow(time, saturation);
        }
        else {
            color = Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).guiColor.getAsColor().getRGB();
        }
        Gui.func_73734_a(this.x, this.y, this.x + this.width, this.y + this.height - 2, color);
        if (font.isEnabled()) {
            Notorious.INSTANCE.fontRenderer.drawStringWithShadow(this.category.name(), this.x + 3.0f, this.y + 5.0f, Color.WHITE);
        }
        else {
            Minecraft.func_71410_x().field_71466_p.func_175063_a(this.category.name(), this.x + 3.0f, this.y + 3.0f, new Color(255, 255, 255).getRGB());
        }
        int yOffset = this.height;
        for (final AbstractToggleContainer button : this.buttons) {
            button.x = this.x;
            button.y = this.y + yOffset;
            yOffset += button.getTotalHeight();
            button.render(mouseX, mouseY, partialTicks);
        }
        this.updateDragPosition(mouseX, mouseY);
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isMouseInside(mouseX, mouseY) && mouseButton == 0) {
            this.startDragging(mouseX, mouseY);
        }
        for (final AbstractToggleContainer button : this.buttons) {
            button.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        if (mouseButton == 0) {
            this.stopDragging(mouseX, mouseY);
        }
        for (final AbstractToggleContainer button : this.buttons) {
            button.mouseReleased(mouseX, mouseY, mouseButton);
        }
    }
    
    @Override
    public void keyTyped(final char keyChar, final int keyCode) {
        for (final AbstractToggleContainer button : this.buttons) {
            button.keyTyped(keyChar, keyCode);
        }
    }
    
    public ArrayList<AbstractToggleContainer> getButtons() {
        return this.buttons;
    }
}
