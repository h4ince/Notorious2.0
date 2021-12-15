// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.gui;

import java.io.IOException;
import java.util.Iterator;
import me.gavin.notorious.hack.Hack;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;

public class ClickGuiScreen extends GuiScreen
{
    private final ArrayList<Panel> panels;
    
    public ClickGuiScreen() {
        this.panels = new ArrayList<Panel>();
        int xoffset = 0;
        for (final Hack.Category category : Hack.Category.values()) {
            this.panels.add(new Panel(category, 10 + xoffset, 10, 100, 15));
            xoffset += 100;
        }
    }
    
    public void func_73863_a(final int mouseX, final int mouseY, final float partialTicks) {
        for (final Panel panel : this.panels) {
            panel.render(mouseX, mouseY, partialTicks);
        }
    }
    
    public void func_73864_a(final int mouseX, final int mouseY, final int mouseButton) {
        for (final Panel panel : this.panels) {
            panel.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }
    
    public void func_146286_b(final int mouseX, final int mouseY, final int mouseButton) {
        for (final Panel panel : this.panels) {
            panel.mouseReleased(mouseX, mouseY, mouseButton);
        }
    }
    
    public void func_73869_a(final char keychar, final int keycode) throws IOException {
        for (final Panel panel : this.panels) {
            panel.keyTyped(keychar, keycode);
        }
        super.func_73869_a(keychar, keycode);
    }
    
    public boolean func_73868_f() {
        return false;
    }
}
