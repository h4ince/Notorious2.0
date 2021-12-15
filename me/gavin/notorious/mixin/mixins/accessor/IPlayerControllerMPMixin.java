// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.mixin.mixins.accessor;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ PlayerControllerMP.class })
public interface IPlayerControllerMPMixin
{
    @Accessor("curBlockDamageMP")
    float getCurBlockDamageMP();
    
    @Accessor("curBlockDamageMP")
    void setCurBlockDamageMP(final float p0);
}
