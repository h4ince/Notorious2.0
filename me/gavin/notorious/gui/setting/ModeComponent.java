// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.gui.setting;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import java.awt.Color;
import me.gavin.notorious.util.ColorUtil;
import me.gavin.notorious.hack.hacks.client.ClickGUI;
import me.gavin.notorious.Notorious;
import me.gavin.notorious.hack.hacks.client.Font;
import me.gavin.notorious.setting.ModeSetting;
import me.gavin.notorious.gui.api.SettingComponent;

public class ModeComponent extends SettingComponent
{
    private final ModeSetting setting;
    
    public ModeComponent(final ModeSetting setting, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.setting = setting;
    }
    
    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
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
        Gui.func_73734_a(this.x, this.y, this.x + this.width, this.y + this.height, new Color(0, 0, 0, (int)Notorious.INSTANCE.hackManager.getHack(ClickGUI.class).backgroundAlpha.getValue()).getRGB());
        Gui.func_73734_a(this.x, this.y, this.x + 2, this.y + this.height, color);
        if (font.isEnabled()) {
            Notorious.INSTANCE.fontRenderer.drawStringWithShadow(this.setting.getName() + " <" + this.setting.getMode() + ">", this.x + 9.0f, this.y + 3.0f, Color.WHITE);
        }
        else {
            Minecraft.func_71410_x().field_71466_p.func_175063_a(this.setting.getName() + " <" + this.setting.getMode() + ">", this.x + 9.0f, this.y + 1.0f, new Color(255, 255, 255).getRGB());
        }
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isMouseInside(mouseX, mouseY)) {
            if (mouseButton == 0) {
                this.setting.cycle(false);
            }
            else if (mouseButton == 1) {
                this.setting.cycle(true);
            }
        }
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
    }
    
    @Override
    public void keyTyped(final char keyChar, final int keyCode) {
    }
    
    @Override
    public int getTotalHeight() {
        return this.height;
    }
}
