// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.mixin.mixins.accessor;

import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Entity.class })
public interface IEntityMixin
{
    @Accessor("inPortal")
    boolean inPortalAccessor();
}
