// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.event.events;

import net.minecraftforge.fml.common.eventhandler.Event;

public class PlayerModelRotationEvent extends Event
{
    private float yaw;
    private float pitch;
    private float bodyYaw;
    
    public PlayerModelRotationEvent(final float yaw, final float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void setBodyYaw(final float yaw) {
        this.bodyYaw = yaw;
    }
    
    public float getBodyYaw() {
        return this.bodyYaw;
    }
}
