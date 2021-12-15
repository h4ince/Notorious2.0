// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.setting;

public class StringSetting extends Setting
{
    private String string;
    
    public StringSetting(final String name, final String typeable) {
        super(name);
        this.string = typeable;
    }
    
    public String getString() {
        return this.string;
    }
    
    public void setString(String set) {
        set = this.string;
    }
}
