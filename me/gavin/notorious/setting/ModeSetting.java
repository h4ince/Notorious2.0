// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.setting;

public class ModeSetting extends Setting
{
    private int modeIndex;
    private String[] modes;
    
    public ModeSetting(final String name, final String value, final String... modes) {
        super(name);
        this.modes = modes;
        this.modeIndex = this.getIndex(value);
    }
    
    public String getMode() {
        return this.modes[this.modeIndex];
    }
    
    public void setMode(final String value) {
        this.modeIndex = this.getIndex(value);
    }
    
    public void cycle(final boolean backwards) {
        if (!backwards) {
            if (this.modeIndex == this.modes.length - 1) {
                this.modeIndex = 0;
            }
            else {
                ++this.modeIndex;
            }
        }
        else if (this.modeIndex == 0) {
            this.modeIndex = this.modes.length - 1;
        }
        else {
            --this.modeIndex;
        }
    }
    
    public int getIndex(final String value) {
        for (int i = 0; i < this.modes.length; ++i) {
            if (this.modes[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
}
