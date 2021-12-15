// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.mixin.mixins.accessor;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Minecraft.class })
public interface IMinecraftMixin
{
    @Accessor("rightClickDelayTimer")
    void setRightClickDelayTimerAccessor(final int p0);
}
