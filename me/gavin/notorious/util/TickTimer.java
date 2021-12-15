// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.util;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.common.MinecraftForge;

public class TickTimer
{
    private long ticksPassed;
    private long lastTicks;
    
    public TickTimer() {
        this.ticksPassed = 0L;
        this.lastTicks = this.ticksPassed;
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        ++this.ticksPassed;
    }
    
    public boolean hasTicksPassed(final long ticks) {
        return this.ticksPassed - this.lastTicks > ticks;
    }
    
    public void reset() {
        this.lastTicks = this.ticksPassed;
    }
}
