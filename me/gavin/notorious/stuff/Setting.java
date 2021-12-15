// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.stuff;

import java.awt.Color;
import java.util.ArrayList;

public class Setting<T>
{
    private final String name;
    private final String description;
    private T value;
    private boolean isOpened;
    private float alpha;
    private final ArrayList<Setting<?>> subSettings;
    
    public Setting(final String name, final String description, final T value) {
        this.alpha = 0.2f;
        this.subSettings = new ArrayList<Setting<?>>();
        this.name = name;
        this.description = description;
        this.value = value;
    }
    
    public Setting(final Setting<?> parent, final String name, final String description, final T value) {
        this.alpha = 0.2f;
        this.subSettings = new ArrayList<Setting<?>>();
        this.name = name;
        this.description = description;
        this.value = value;
        if (parent.getValue() instanceof Boolean) {
            final Setting<Boolean> booleanSetting = (Setting<Boolean>)parent;
            booleanSetting.addSubSetting(this);
        }
        if (parent.getValue() instanceof Enum) {
            final Setting<Enum<?>> enumSetting = (Setting<Enum<?>>)parent;
            enumSetting.addSubSetting(this);
        }
        if (parent.getValue() instanceof Color) {
            final Setting<Color> colorSetting = (Setting<Color>)parent;
            colorSetting.addSubSetting(this);
        }
        if (parent.getValue() instanceof Integer) {
            final NumberSetting<Integer> integerNumberSetting = (NumberSetting<Integer>)(NumberSetting)parent;
            integerNumberSetting.addSubSetting(this);
        }
        if (parent.getValue() instanceof Double) {
            final NumberSetting<Double> doubleNumberSetting = (NumberSetting<Double>)(NumberSetting)parent;
            doubleNumberSetting.addSubSetting(this);
        }
        if (parent.getValue() instanceof Float) {
            final NumberSetting<Float> floatNumberSetting = (NumberSetting<Float>)(NumberSetting)parent;
            floatNumberSetting.addSubSetting(this);
        }
    }
    
    public ArrayList<Setting<?>> getSubSettings() {
        return this.subSettings;
    }
    
    public boolean hasSubSettings() {
        return this.subSettings.size() > 0;
    }
    
    public void addSubSetting(final Setting<?> subSetting) {
        this.subSettings.add(subSetting);
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public T getValue() {
        return this.value;
    }
    
    public boolean isOpened() {
        return this.isOpened;
    }
    
    public void setValue(final T value) {
        this.value = value;
    }
    
    public void setOpened(final boolean opened) {
        this.isOpened = opened;
    }
    
    public float getAlpha() {
        return this.alpha;
    }
    
    public void setAlpha(final float alpha) {
        this.alpha = alpha;
    }
}
