// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.mixin.mixins.accessor;

import net.minecraft.util.EnumHand;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.network.play.client.CPacketUseEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ CPacketUseEntity.class })
public interface ICPacketUseEntityMixin
{
    @Accessor("entityId")
    void setEntityIdAccessor(final int p0);
    
    @Accessor("action")
    void setActionAccessor(final CPacketUseEntity.Action p0);
    
    @Accessor("hand")
    void setHandAccessor(final EnumHand p0);
}
