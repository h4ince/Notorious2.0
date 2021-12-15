// 
// Decompiled by Procyon v0.5.36
// 

package me.gavin.notorious.mixin.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraftforge.fml.common.eventhandler.Event;
import me.gavin.notorious.event.events.RenderEntityEvent;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.RenderManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ RenderManager.class })
public class RenderManagerMixin
{
    @Inject(method = { "renderEntity" }, at = { @At("HEAD") })
    public void renderEntity$Inject$HEAD(final Entity entityIn, final double x, final double y, final double z, final float yaw, final float partialTicks, final boolean p_188391_10_, final CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post((Event)new RenderEntityEvent.Pre(entityIn, x, y, z));
    }
    
    @Inject(method = { "renderEntity" }, at = { @At("TAIL") })
    public void renderEntity$Inject$TAIL(final Entity entityIn, final double x, final double y, final double z, final float yaw, final float partialTicks, final boolean p_188391_10_, final CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post((Event)new RenderEntityEvent.Post(entityIn, x, y, z));
    }
}
