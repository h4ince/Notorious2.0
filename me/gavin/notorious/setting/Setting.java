// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.setting;

import java.util.Iterator;
import me.gavin.notorious.hack.Hack;

public class Setting
{
    private final String name;
    private Hack hack;
    private SettingGroup valueGroup;
    
    public Setting(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public static Setting getFromDisplayString(final String name) {
        for (final Setting setting : SettingGroup.getValues()) {
            if (setting.getName().equalsIgnoreCase(name)) {
                return setting;
            }
        }
        return null;
    }
    
    public void setHack(final Hack hack) {
        this.hack = hack;
    }
    
    public void setGroup(final SettingGroup group) {
        this.valueGroup = group;
        SettingGroup.getValues().add(this);
    }
}
