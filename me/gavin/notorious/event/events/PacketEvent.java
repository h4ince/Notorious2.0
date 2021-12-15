// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.event.events;

import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class PacketEvent extends Event
{
    private final Packet<?> packet;
    
    private PacketEvent(final Packet<?> packet) {
        this.packet = packet;
    }
    
    public Packet<?> getPacket() {
        return this.packet;
    }
    
    public static class Send extends PacketEvent
    {
        public Send(final Packet<?> packet) {
            super(packet, null);
        }
    }
    
    public static class Receive extends PacketEvent
    {
        public Receive(final Packet<?> packet) {
            super(packet, null);
        }
    }
}
