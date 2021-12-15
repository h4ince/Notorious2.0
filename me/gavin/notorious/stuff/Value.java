// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.stuff;

import java.util.function.Predicate;

public class Value<T>
{
    public String name;
    public String[] alias;
    public String desc;
    public Predicate<T> showIf;
    public T value;
    public T min;
    public T max;
    
    public Value(final String name, final String[] alias, final String desc) {
        this.name = name;
        this.alias = alias;
        this.desc = desc;
    }
    
    public Value(final String name, final String[] alias, final String desc, final T value) {
        this(name, alias, desc);
        this.value = value;
    }
    
    public Value(final String name, final String[] alias, final String desc, final T value, final T min, final T max) {
        this(name, alias, desc, value);
        this.min = min;
        this.max = max;
    }
    
    public Value(final String name, final String desc) {
        this.name = name;
        this.alias = new String[0];
        this.desc = desc;
    }
    
    public Value(final String name, final String desc, final T value) {
        this(name, new String[0], desc);
        this.value = value;
    }
    
    public Value(final String name, final String desc, final T value, final T min, final T max) {
        this(name, new String[0], desc, value);
        this.min = min;
        this.max = max;
    }
    
    public Value(final String name) {
        this.name = name;
        this.alias = new String[0];
        this.desc = "";
    }
    
    public Value(final String name, final T value) {
        this(name, new String[0], "");
        this.value = value;
    }
    
    public Value(final String name, final T value, final T min, final T max) {
        this(name, new String[0], "", value);
        this.min = min;
        this.max = max;
    }
    
    public Value(final String name, final T value, final T min, final T max, final Predicate<T> showIf) {
        this(name, new String[0], "", value);
        this.min = min;
        this.max = max;
        this.showIf = showIf;
    }
    
    public Value(final String name, final T value, final Predicate<T> showIf) {
        this(name, new String[0], "", value);
        this.showIf = showIf;
    }
    
    public <T> T clamp(final T value, final T min, final T max) {
        return (((Comparable)value).compareTo(min) < 0) ? min : ((((Comparable)value).compareTo(max) > 0) ? max : value);
    }
    
    public T getValue() {
        return this.value;
    }
    
    public void setValue(final T value) {
        this.value = value;
    }
    
    public String getMeta() {
        if (this.value != null) {
            return this.value.toString();
        }
        return this.name;
    }
    
    public boolean isVisible() {
        return this.showIf == null || this.showIf.test(this.getValue());
    }
}
