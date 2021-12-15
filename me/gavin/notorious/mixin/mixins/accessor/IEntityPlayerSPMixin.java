// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.mixin.mixins.accessor;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityPlayerSP.class })
public interface IEntityPlayerSPMixin
{
    @Accessor("positionUpdateTicks")
    void positionUpdateTicksAccessor(final int p0);
    
    @Accessor("positionUpdateTicks")
    int getPositionUpdateTicksAccessor();
}
