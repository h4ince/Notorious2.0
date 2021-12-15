// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.mixin.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import me.gavin.notorious.event.events.EntityRemoveEvent;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ World.class })
public class MixinWorld
{
    @Inject(method = { "onEntityRemoved" }, at = { @At("HEAD") }, cancellable = true)
    public void onEntityRemoved(final Entity entity, final CallbackInfo info) {
        final EntityRemoveEvent event = new EntityRemoveEvent(entity);
        MinecraftForge.EVENT_BUS.post((Event)event);
    }
}
