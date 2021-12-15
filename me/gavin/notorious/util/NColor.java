// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util;

import java.awt.Color;

public class NColor
{
    private int red;
    private int green;
    private int blue;
    private int alpha;
    
    public NColor(final int red, final int green, final int blue, final int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }
    
    public NColor(final int red, final int green, final int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = 255;
    }
    
    public NColor(final Color color) {
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.alpha = color.getAlpha();
    }
    
    public int getRed() {
        return this.red;
    }
    
    public void setRed(final int red) {
        this.red = red;
    }
    
    public int getGreen() {
        return this.green;
    }
    
    public void setGreen(final int green) {
        this.green = green;
    }
    
    public int getBlue() {
        return this.blue;
    }
    
    public void setBlue(final int blue) {
        this.blue = blue;
    }
    
    public int getAlpha() {
        return this.alpha;
    }
    
    public void setAlpha(final int alpha) {
        this.alpha = alpha;
    }
    
    public int getRGB() {
        return (this.alpha & 0xFF) << 24 | (this.red & 0xFF) << 16 | (this.green & 0xFF) << 8 | (this.blue & 0xFF);
    }
    
    public Color getAsColor() {
        return new Color(this.red, this.green, this.blue, this.alpha);
    }
}
