// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.setting;

import java.util.ArrayList;

public class SettingGroup
{
    private final String name;
    private static ArrayList<Setting> values;
    
    public SettingGroup(final String name) {
        this.name = name;
        SettingGroup.values = new ArrayList<Setting>();
    }
    
    public String getName() {
        return this.name;
    }
    
    public static ArrayList<Setting> getValues() {
        return SettingGroup.values;
    }
}
