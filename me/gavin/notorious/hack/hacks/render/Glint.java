// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.render;

import java.awt.Color;
import me.gavin.notorious.setting.BooleanSetting;
import me.gavin.notorious.setting.NumSetting;
import me.gavin.notorious.hack.RegisterSetting;
import me.gavin.notorious.setting.ColorSetting;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Glint", description = "ez", category = Category.Render)
public class Glint extends Hack
{
    @RegisterSetting
    public final ColorSetting colorShit;
    @RegisterSetting
    public static NumSetting saturation;
    @RegisterSetting
    public final BooleanSetting rainbow;
    
    public Glint() {
        this.colorShit = new ColorSetting("Color", 255, 255, 255, 255);
        this.rainbow = new BooleanSetting("Rainbow", true);
    }
    
    public static Color getColor(final long offset, final float fade) {
        final float hue = (System.nanoTime() + offset) / 1.0E10f % 1.0f;
        final long color = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(hue, Glint.saturation.getValue(), 1.0f)), 16);
        final Color c = new Color((int)color);
        return new Color(c.getRed() / 255.0f * fade, c.getGreen() / 255.0f * fade, c.getBlue() / 255.0f * fade, c.getAlpha() / 255.0f);
    }
    
    static {
        Glint.saturation = new NumSetting("Saturation", 1.0f, 0.1f, 1.0f, 0.1f);
    }
}
