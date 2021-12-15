// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.hack.hacks.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import me.gavin.notorious.mixin.mixins.accessor.ISPacketExplosionMixin;
import net.minecraft.network.play.server.SPacketExplosion;
import me.gavin.notorious.event.events.PacketEvent;
import me.gavin.notorious.hack.RegisterHack;
import me.gavin.notorious.hack.Hack;

@RegisterHack(name = "Velocity", description = "Cancel packets that give velocity", category = Category.Movement)
public class Velocity extends Hack
{
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketExplosion) {
            final SPacketExplosion explosion = (SPacketExplosion)event.getPacket();
            final ISPacketExplosionMixin accessor = (ISPacketExplosionMixin)explosion;
            accessor.setMotionXAccessor(0.0f);
            accessor.setMotionYAccessor(0.0f);
            accessor.setMotionZAccessor(0.0f);
        }
        else if (event.getPacket() instanceof SPacketEntityVelocity && ((SPacketEntityVelocity)event.getPacket()).func_149412_c() == Velocity.mc.field_71439_g.func_145782_y()) {
            event.setCanceled(true);
        }
    }
}
