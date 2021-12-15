// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util;

public class AnimationUtil
{
    public static float getSmooth2Animation(final int duration, final int time) {
        final double x1 = time / (float)duration;
        return (float)(6.0 * Math.pow(x1, 5.0) - 15.0 * Math.pow(x1, 4.0) + 10.0 * Math.pow(x1, 3.0));
    }
    
    public static float getSmooth2Animation(final float duration, final float time) {
        final double x1 = time / duration;
        return (float)(6.0 * Math.pow(x1, 5.0) - 15.0 * Math.pow(x1, 4.0) + 10.0 * Math.pow(x1, 3.0));
    }
}
