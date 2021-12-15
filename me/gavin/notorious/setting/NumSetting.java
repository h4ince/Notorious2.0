// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.setting;

public class NumSetting extends Setting
{
    private float value;
    private final float min;
    private final float max;
    private final float increment;
    
    public NumSetting(final String name, final float value, final float min, final float max, final float increment) {
        super(name);
        this.value = value;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }
    
    public void setValue(final float value) {
        this.value = Math.max(this.min, Math.min(value, this.max));
    }
    
    public float getValue() {
        return this.value;
    }
    
    public float getMin() {
        return this.min;
    }
    
    public float getMax() {
        return this.max;
    }
    
    public float getIncrement() {
        return this.increment;
    }
}
