// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.stuff;

public final class NumberSetting<T extends Number> extends Setting<T>
{
    private final T min;
    private final T max;
    private final int scale;
    
    public NumberSetting(final String name, final String description, final T min, final T value, final T max, final int scale) {
        super(name, description, value);
        this.min = min;
        this.max = max;
        this.scale = scale;
    }
    
    public NumberSetting(final Setting<?> parent, final String name, final String description, final T min, final T value, final T max, final int scale) {
        super(parent, name, description, value);
        this.min = min;
        this.max = max;
        this.scale = scale;
    }
    
    public T getMin() {
        return this.min;
    }
    
    public T getMax() {
        return this.max;
    }
    
    public int getScale() {
        return this.scale;
    }
}
