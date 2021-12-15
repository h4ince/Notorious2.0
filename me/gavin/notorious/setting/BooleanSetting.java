// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.setting;

import me.gavin.notorious.gui.api.Toggleable;

public class BooleanSetting extends Setting implements Toggleable
{
    private boolean value;
    
    public BooleanSetting(final String name, final boolean value) {
        super(name);
        this.value = value;
    }
    
    public void setValue(final boolean value) {
        this.value = value;
    }
    
    public void toggle() {
        this.value = !this.value;
    }
    
    @Override
    public boolean isEnabled() {
        return this.value;
    }
    
    public boolean getValue() {
        return this.isEnabled();
    }
}
