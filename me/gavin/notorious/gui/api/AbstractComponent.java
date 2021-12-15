// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.gui.api;

public abstract class AbstractComponent extends Rect
{
    public AbstractComponent(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
    }
    
    public abstract void render(final int p0, final int p1, final float p2);
    
    public abstract void mouseClicked(final int p0, final int p1, final int p2);
    
    public abstract void mouseReleased(final int p0, final int p1, final int p2);
    
    public abstract void keyTyped(final char p0, final int p1);
    
    public void setPos(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
}
