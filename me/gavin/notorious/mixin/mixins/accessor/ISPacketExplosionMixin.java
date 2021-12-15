// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.mixin.mixins.accessor;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.network.play.server.SPacketExplosion;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ SPacketExplosion.class })
public interface ISPacketExplosionMixin
{
    @Accessor("motionX")
    void setMotionXAccessor(final float p0);
    
    @Accessor("motionY")
    void setMotionYAccessor(final float p0);
    
    @Accessor("motionZ")
    void setMotionZAccessor(final float p0);
}
