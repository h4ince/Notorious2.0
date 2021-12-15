// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.event.events;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.Event;

public abstract class RenderEntityEvent extends Event
{
    private final Entity entity;
    private final double x;
    private final double y;
    private final double z;
    
    private RenderEntityEvent(final Entity entity, final double x, final double y, final double z) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public static class Pre extends RenderEntityEvent
    {
        public Pre(final Entity entity, final double x, final double y, final double z) {
            super(entity, x, y, z, null);
        }
    }
    
    public static class Post extends RenderEntityEvent
    {
        public Post(final Entity entity, final double x, final double y, final double z) {
            super(entity, x, y, z, null);
        }
    }
}
