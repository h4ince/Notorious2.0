// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.event.events;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class EntityRemoveEvent extends Event
{
    private final Entity entity;
    
    public EntityRemoveEvent(final Entity entity) {
        this.entity = entity;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
}
