// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util;

import net.minecraft.util.math.MathHelper;
import java.awt.Color;

public class ColorUtil
{
    public static int getRainbow(final float time, final float saturation) {
        final float hue = System.currentTimeMillis() % (int)(time * 1000.0f) / (time * 1000.0f);
        return Color.HSBtoRGB(hue, saturation, 1.0f);
    }
    
    public static int getRGBWave(final float seconds, final float saturation, final long index) {
        final float hue = (System.currentTimeMillis() + index) % (int)(seconds * 1000.0f) / (seconds * 1000.0f);
        return Color.HSBtoRGB(hue, saturation, 1.0f);
    }
    
    public static Color colorRainbow(final int delay, final float saturation, final float brightness) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        return Color.getHSBColor((float)((rainbowState %= 360.0) / 360.0), saturation, brightness);
    }
    
    public static Color normalizedFade(final float value) {
        final float green = 1.0f - value;
        return new Color(value, green, 0.0f);
    }
    
    public static Color normalizedFade(final float value, final Color startColor, final Color endColor) {
        final float sr = startColor.getRed() / 255.0f;
        final float sg = startColor.getGreen() / 255.0f;
        final float sb = startColor.getBlue() / 255.0f;
        final float er = endColor.getRed() / 255.0f;
        final float eg = endColor.getGreen() / 255.0f;
        final float eb = endColor.getBlue() / 255.0f;
        final float r = MathHelper.func_76131_a(sr * value + er * (1.0f - value), 0.0f, 1.0f);
        final float g = MathHelper.func_76131_a(sg * value + eg * (1.0f - value), 0.0f, 1.0f);
        final float b = MathHelper.func_76131_a(sb * value + eb * (1.0f - value), 0.0f, 1.0f);
        return new Color(r, g, b);
    }
    
    public static Color brightenFade(final float value, final Color color) {
        final float sr = color.getRed() / 255.0f;
        final float sg = color.getGreen() / 255.0f;
        final float sb = color.getBlue() / 255.0f;
        final float er = color.getRed() / 3.0f;
        final float eg = color.getGreen() / 3.0f;
        final float eb = color.getBlue() / 3.0f;
        final float r = MathHelper.func_76131_a(sr * value + er * (4.0f - value), 0.0f, 1.0f);
        final float g = MathHelper.func_76131_a(sg * value + eg * (4.0f - value), 0.0f, 1.0f);
        final float b = MathHelper.func_76131_a(sb * value + eb * (4.0f - value), 0.0f, 1.0f);
        return new Color(r, g, b);
    }
    
    public static Color getColorFlow(final double time, final double speed, final Color startColor, final Color endColor) {
        final float sin = (float)(Math.sin(System.currentTimeMillis() / speed + time) * 0.5) + 0.5f;
        return normalizedFade(sin, startColor, endColor);
    }
    
    public static Color getColorBrighten(final double time, final double speed, final Color startColor, final Color endColor) {
        final float sin = (float)(Math.sin(System.currentTimeMillis() / speed + time) * 0.5) + 0.5f;
        return normalizedFade(sin, startColor, endColor);
    }
}
