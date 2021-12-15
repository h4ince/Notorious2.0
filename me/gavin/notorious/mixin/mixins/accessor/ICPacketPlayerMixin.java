// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.mixin.mixins.accessor;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.network.play.client.CPacketPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ CPacketPlayer.class })
public interface ICPacketPlayerMixin
{
    @Accessor("yaw")
    void setYawAccessor(final float p0);
    
    @Accessor("pitch")
    void setPitchAccessor(final float p0);
}
