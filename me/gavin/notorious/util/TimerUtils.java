// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util;

public class TimerUtils
{
    public long lastMilliseconds;
    
    public TimerUtils() {
        this.lastMilliseconds = System.currentTimeMillis();
    }
    
    public boolean hasTimeElapsed(final long time) {
        return System.currentTimeMillis() - this.lastMilliseconds > time;
    }
    
    public void reset() {
        this.lastMilliseconds = System.currentTimeMillis();
    }
}
