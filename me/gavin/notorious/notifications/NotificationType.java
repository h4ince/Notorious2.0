// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.notifications;

import java.awt.Color;

public enum NotificationType
{
    INFO(new Color(-10000385)), 
    WARNING(new Color(-17049)), 
    ERROR(new Color(-39065));
    
    Color color;
    
    private NotificationType(final Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        return this.color;
    }
}
