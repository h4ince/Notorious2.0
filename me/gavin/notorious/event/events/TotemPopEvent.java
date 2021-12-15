// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.event.events;

import net.minecraftforge.fml.common.eventhandler.Event;

public class TotemPopEvent extends Event
{
    private final String name;
    private final int popCount;
    private final int entId;
    
    public TotemPopEvent(final String name, final int count, final int entId) {
        this.name = name;
        this.popCount = count;
        this.entId = entId;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getPopCount() {
        return this.popCount;
    }
    
    public int getEntityId() {
        return this.entId;
    }
}
