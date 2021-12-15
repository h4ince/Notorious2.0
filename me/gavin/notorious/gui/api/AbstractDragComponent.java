// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.gui.api;

public abstract class AbstractDragComponent extends AbstractComponent
{
    private int dragX;
    private int dragY;
    public boolean dragging;
    
    public AbstractDragComponent(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
    }
    
    public void startDragging(final int mouseX, final int mouseY) {
        if (this.isMouseInside(mouseX, mouseY)) {
            this.dragging = true;
            this.dragX = mouseX - this.x;
            this.dragY = mouseY - this.y;
        }
    }
    
    public void stopDragging(final int mouseX, final int mouseY) {
        this.dragging = false;
    }
    
    public void updateDragPosition(final int mouseX, final int mouseY) {
        if (this.dragging) {
            this.x = mouseX - this.dragX;
            this.y = mouseY - this.dragY;
        }
    }
}
