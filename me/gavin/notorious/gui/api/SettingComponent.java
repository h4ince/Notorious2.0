// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.gui.api;

public abstract class SettingComponent extends AbstractComponent
{
    public SettingComponent(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
    }
    
    public abstract int getTotalHeight();
}
