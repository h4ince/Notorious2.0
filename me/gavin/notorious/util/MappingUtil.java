// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util;

import net.minecraft.client.Minecraft;

public class MappingUtil
{
    public static final String tickLength;
    public static final String timer;
    
    public static boolean isObfuscated() {
        try {
            return Minecraft.class.getDeclaredField("instance") == null;
        }
        catch (Exception var1) {
            return true;
        }
    }
    
    static {
        tickLength = (isObfuscated() ? "field_194149_e" : "tickLength");
        timer = (isObfuscated() ? "field_71428_T" : "timer");
    }
}
