// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.gui.api;

public class Rect
{
    public int x;
    public int y;
    public int width;
    public int height;
    
    public Rect(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public boolean isMouseInside(final int mouseX, final int mouseY) {
        return mouseX > this.x && mouseX < this.x + this.width && mouseY > this.y && mouseY < this.y + this.height;
    }
}
