// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.setting;

import java.awt.Color;
import me.gavin.notorious.util.NColor;

public class ColorSetting extends Setting
{
    private final NumSetting hue;
    private final NumSetting saturation;
    private final NumSetting brightness;
    private final NumSetting alpha;
    
    public ColorSetting(final String name, final NColor color) {
        super(name);
        final float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        this.hue = new NumSetting("Hue", hsb[0], 0.0f, 1.0f, 0.01f);
        this.saturation = new NumSetting("Saturation", hsb[1], 0.0f, 1.0f, 0.01f);
        this.brightness = new NumSetting("Brightness", hsb[2], 0.0f, 1.0f, 0.01f);
        this.alpha = new NumSetting("A", (float)color.getAlpha(), 0.0f, 255.0f, 1.0f);
    }
    
    public ColorSetting(final String name, final int red, final int green, final int blue, final int alpha) {
        this(name, new NColor(red, green, blue, alpha));
    }
    
    public ColorSetting(final String name, final Color color) {
        this(name, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
    
    public NumSetting getHue() {
        return this.hue;
    }
    
    public NumSetting getSaturation() {
        return this.saturation;
    }
    
    public NumSetting getBrightness() {
        return this.brightness;
    }
    
    public NumSetting getAlpha() {
        return this.alpha;
    }
    
    public Color getAsColor() {
        return new Color(Color.HSBtoRGB(this.hue.getValue(), this.saturation.getValue(), this.brightness.getValue()));
    }
}
