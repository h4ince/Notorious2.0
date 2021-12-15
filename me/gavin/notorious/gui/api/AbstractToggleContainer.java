// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.gui.api;

import java.util.ArrayList;

public abstract class AbstractToggleContainer extends AbstractComponent
{
    protected boolean open;
    protected final Toggleable toggleable;
    protected final ArrayList<SettingComponent> components;
    
    public AbstractToggleContainer(final Toggleable toggleable, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.toggleable = toggleable;
        this.components = new ArrayList<SettingComponent>();
    }
    
    public Toggleable getToggleable() {
        return this.toggleable;
    }
    
    public abstract int getTotalHeight();
    
    public ArrayList<SettingComponent> getComponents() {
        return this.components;
    }
}
