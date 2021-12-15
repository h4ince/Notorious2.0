// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.mixin.mixins;

import me.gavin.notorious.event.events.PlayerWalkingUpdateEvent;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import me.gavin.notorious.event.events.PlayerLivingUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityPlayerSP.class })
public class EntityPlayerSPMixin
{
    @Inject(method = { "onLivingUpdate" }, at = { @At("HEAD") })
    public void onLivingUpdateInject(final CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post((Event)new PlayerLivingUpdateEvent());
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("HEAD") })
    public void onUpdateWalkingPlayerInjectPre(final CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post((Event)new PlayerWalkingUpdateEvent.Pre());
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("TAIL") })
    public void onUpdateWalkingPlayerInjectPost(final CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post((Event)new PlayerWalkingUpdateEvent.Post());
    }
}
